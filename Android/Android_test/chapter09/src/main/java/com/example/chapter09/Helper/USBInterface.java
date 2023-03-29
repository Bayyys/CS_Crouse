package com.example.chapter09.Helper;

/**
 * USBHelper连接时状态说明
 */
public class USBInterface {
    static int usb_ok = 0; // USB正常打开

    static int usb_permission_ok = 10001;  // USB授权成功
    static int usb_permission_fail = 10002;//USB授权失败
    static int usb_find_this_fail = 10003;//没有找到指定设备
    int usb_find_all_fail = 10004;//没有找到任何设备
    static int usb_open_fail = 10005;//USB设备打开失败
    static int usb_passway_fail = 10006;//USB通道打开失败
    int usb_send_data_ok = 10007;//USB发送数据成功
    int usb_send_data_fail = 10008;//USB发送数据失败

}
