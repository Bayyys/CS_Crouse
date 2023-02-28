package com.example.chapter07_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds;

import com.example.chapter07_client.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_contact_name;
    private EditText et_contact_phone;
    private EditText et_contact_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);

        et_contact_name = findViewById(R.id.et_contact_name);
        et_contact_phone = findViewById(R.id.et_contact_phone);
        et_contact_email = findViewById(R.id.et_contact_email);
        findViewById(R.id.btn_add_contact).setOnClickListener(this);
        findViewById(R.id.btn_read_contact).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_contact:
                Contact contact = new Contact();
                Contact.name = et_contact_name.getText().toString();
                Contact.phone = et_contact_phone.getText().toString();
                Contact.email = et_contact_email.getText().toString();

                // 方式一，通过ContentResolver多次写入，每次一个字段
//                addContact(getContentResolver(), contact);

                // 方式二，批处理方式
                // 每一次操作都是一个 ContentProviderOperation 对象，构建一个操作集合，然后一次性执行
                // 保证了事物的一致性
                addFullContacts(getContentResolver(), contact);


                break;
            case R.id.btn_read_contact:
                readPhoneContacts(getContentResolver());
                break;
        }
    }

    @SuppressLint("Range")
    private void readPhoneContacts(ContentResolver resolver) {
        // 先查询 raw_contacts 表，获取联系人的id
        Cursor cursor = resolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{ContactsContract.RawContacts._ID}, null, null, null);
        while (cursor.moveToNext()) {
            int rawContactID = cursor.getInt(0);
            // 再根据联系人的id查询 data 表，获取联系人的姓名、电话、邮箱等信息
            Uri uri = Uri.parse("content://com.android.contacts/contacts/" + rawContactID + "/data");
            Cursor dataCursor = resolver.query(uri, new String[]{ContactsContract.Data.MIMETYPE, ContactsContract.Data.DATA1, ContactsContract.Data.DATA2}, null, null, null);
            Contact contact = new Contact();
            while (dataCursor.moveToNext()) {
                String data1 = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DATA1));
                String miniType = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
                switch (miniType) {
                    case CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE:
                        contact.name = data1;
                        break;
                    case CommonDataKinds.Phone.CONTENT_ITEM_TYPE:
                        contact.phone = data1;
                        break;
                    case CommonDataKinds.Email.CONTENT_ITEM_TYPE:
                        contact.email = data1;
                        break;
                }
            }
            dataCursor.close();
            if (contact.name != null) {
                Log.d("bay", contact.toString());
            }
        }
    }

    // 向手机通讯录一次性添加一个联系人信息（包括姓名、电话、邮箱）
    private void addFullContacts(ContentResolver resolver, Contact contact) {
        // 创建一个插入联系人记录的内容操作器
        ContentProviderOperation op_main = ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build();

        // 创建一个插入联系人姓名记录的内容操作器
        ContentProviderOperation op_name = ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(CommonDataKinds.StructuredName.GIVEN_NAME, contact.name)
                .build();

        // 创建一个插入联系人电话记录的内容操作器
        ContentProviderOperation op_phone = ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(CommonDataKinds.Phone.NUMBER, contact.phone)
                .withValue(CommonDataKinds.Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE)
                .build();

        // 创建一个插入联系人邮箱记录的内容操作器
        ContentProviderOperation op_email = ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(CommonDataKinds.Email.DATA, contact.email)
                .withValue(CommonDataKinds.Email.TYPE, CommonDataKinds.Email.TYPE_WORK)
                .build();

        // 将上面的操作器添加到操作集合中
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        operations.add(op_main);
        operations.add(op_name);
        operations.add(op_phone);
        operations.add(op_email);

        try {
            resolver.applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (OperationApplicationException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    // 往手机通讯录中添加一个联系人信息（包括姓名、电话、邮箱）
    private void addContact(ContentResolver resolver, Contact contact) {
        ContentValues values = new ContentValues();
        // 往 raw_contacts 中添加联系人记录，并获取添加后的联系人编号
        Uri uri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(uri);

        ContentValues name = new ContentValues();
        // 关联联系人编号
        name.put(Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // “姓名”的数据类型
        name.put(Contacts.Data.MIMETYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 联系人姓名
        name.put(Contacts.Data.DATA2, contact.name);
        // 添加联系人的姓名记录
        resolver.insert(ContactsContract.Data.CONTENT_URI, name);


        ContentValues phone = new ContentValues();
        // 关联联系人编号
        phone.put(Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // “电话”的数据类型
        phone.put(Contacts.Data.MIMETYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        // 联系人电话
        phone.put(Contacts.Data.DATA1, contact.phone);
        // 联系人电话类型
        phone.put(Contacts.Data.DATA2, CommonDataKinds.Phone.TYPE_MOBILE);
        // 添加联系人的电话记录
        resolver.insert(ContactsContract.Data.CONTENT_URI, phone);

        ContentValues email = new ContentValues();
        // 关联联系人编号
        email.put(Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // “邮箱”的数据类型
        email.put(Contacts.Data.MIMETYPE, CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        // 联系人邮箱
        email.put(Contacts.Data.DATA1, contact.email);
        // 联系人邮箱类型。1表示家庭，2表示工作
        email.put(Contacts.Data.DATA2, CommonDataKinds.Email.TYPE_WORK);
        // 添加联系人的邮箱记录
        resolver.insert(ContactsContract.Data.CONTENT_URI, email);

    }
}