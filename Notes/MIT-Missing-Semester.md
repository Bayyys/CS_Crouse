# MIT-Missing-Semester

## 课程简介

- 先修要求：无
- 编程语言：shell
- 课程难度：🌟🌟
- 预计学时：10 小时

正如课程名字所言：“计算机教f学中消失的一个学期”，这门课将会教会你许多大学的课堂上不会涉及但却对每个 CSer 无比重要的工具或者知识点。例如 Shell 编程、命令行配置、Git、Vim、`tmux`、`ssh` 等等。如果你是一个计算机小白，那么我非常建议你学习一下这门课，因为它基本涉及了本书必学工具中的绝大部分内容。

除了 MIT 官方的学习资料外，北京大学图灵班开设的前沿计算实践中也开设了相关课程，资料位于[这个网站](http://vcl.pku.edu.cn/course/PFCII/2021-spring/index.html)下，供大家参考。

## 课程资源

- 课程网站：https://missing.csail.mit.edu/2020/
- 课程视频：https://www.youtube.com/playlist?list=PLyzOVJj3bHQuloKGG59rS43e29ro7I57J
- 课程作业：一些随堂小练习，具体见课程网站。

# 基本命令

## top(持续监听进程运行状态)

- -d 秒数：指定 top 命令每隔几秒更新。默认是 3 秒；
- -b：使用批处理模式输出。一般和"-n"选项合用，用于把 top 命令重定向到文件中；
- -n 次数：指定 top 命令执行的次数。一般和"-"选项合用；
- -p 进程PID：仅查看指定 ID 的进程；
- -s：使 top 命令在安全模式中运行，避免在交互模式中出现错误；
- -u 用户名：只监听某个用户的进程；


在 top 命令的显示窗口中，还可以使用如下按键，进行一下交互操作：

- ? 或 h：显示交互模式的帮助；
- P：按照 CPU 的使用率排序，默认就是此选项；
- M：按照内存的使用率排序；
- N：按照 PID 排序；
- T：按照 CPU 的累积运算时间排序，也就是按照 TIME+ 项排序；
- k：按照 PID 给予某个进程一个信号。一般用于中止某个进程，信号 9 是强制中止的信号；
- r：按照 PID 给某个进程重设优先级（Nice）值；
- q：退出 top 命令；

## jobs(后台进程)、

| 选项           | 含义                                   |
| -------------- | -------------------------------------- |
| -l（L 的小写） | 列出进程的 PID 号。                    |
| -n             | 只列出上次发出通知后改变了状态的进程。 |
| -p             | 只列出进程的 PID 号。                  |
| -r             | 只列出运行中的进程。                   |
| -s             | 只列出已停止的进程                     |

## dpkg --list(查看已安装软件)

```shell
# 查看已安装软件
dpkg --list

# 卸载软件
sudo apt remove --purge [name] #卸载软件同时删除配置文件
sudo apt remove [name]  #卸载该软件
```

# 好用的工具

## vim内插件

### ctrlp(vim 内文件树)

![image-20221121204804627](https://s2.loli.net/2022/11/21/tIhxL1OXejQGdZu.png)

- `ctrl + j/k` 进行上下选择
- ` ctrl + x` 在当前窗口水平分屏打开文件 
- `ctrl + v` 同上, 垂直分屏 
- `ctrl + t `在tab中打开
- `ctrl + r`在 **字符串模式** 和 **正则表达式模式** 之间切换

### NERDTree(文件树)

![image-20221201182125369](https://s2.loli.net/2022/12/01/kH8f53Q1KTUzxhI.png)

- `?`: 快速帮助文档
- `o`: 打开一个目录或者打开文件，创建的是 buffer，也可以用来打开书签
- `go`: 打开一个文件，但是光标仍然留在 NERDTree，创建的是 buffer
- `t`: 打开一个文件，创建的是Tab，对书签同样生效
- `T`: 打开一个文件，但是光标仍然留在 NERDTree，创建的是 Tab，对书签同样生效
- `i`: 水平分割创建文件的窗口，创建的是 buffer
- `gi`: 水平分割创建文件的窗口，但是光标仍然留在 NERDTree
- `s`: 垂直分割创建文件的窗口，创建的是 buffer
- `gs`: 和 gi，go 类似
- `x`: 收起当前打开的目录
- `X`: 收起所有打开的目录
- `e`: 以文件管理的方式打开选中的目录
- `D`: 删除书签

### auto-pairs(括号自动匹配)

### NerdCommenter(注释)

- `\cc` 注释当前行和选中行
- `\cu` 取消注释
-  `\c<space>` 如果被选区域有部分被注释，则对被选区域执行取消注释操作，其它情况执行反转注释操作
- ` \ci` 执行反转注释操作，选中区域注释部分取消注释，非注释部分添加注释
- ` \cA` 跳转到该行结尾添加注释，并进入编辑模式
- ` \cn `没有发现和\cc有区别
- ` \cm` 对被选区域用一对注释符进行注释，前面的注释对每一行都会添加注释
- `\cs` 添加性感的注释，代码开头介绍部分通常使用该注释
-  `\cy` 添加注释，并复制被添加注释的部分
- `\c$` 注释当前光标到改行结尾的内容
- `\ca `转换注释的方式，比如： /\*\*/和//
- `\cl`  `\cb` 左对齐和左右对其，左右对其主要针对/**/

## fd(文件查找)



## shellcheck[^1]\(查错)

![shellcheck](https://s2.loli.net/2022/11/18/bWGyigK2Mm8aeSc.png)

## tldr (Too long; Didn't read)

![image-20221120191539960](https://s2.loli.net/2022/11/20/NuKy618WfhsYAUj.png)

## rg(riggrep)(在当前文件下文件内查找)

![image-20221120193545743](https://s2.loli.net/2022/11/20/75dKjeGfFAt1YwN.png)

## fzf(命令行的模糊搜索)

![image-20221120200210604](https://s2.loli.net/2022/11/20/lvd1MqCVTIUj438.png)

![image-20221120200217174](https://s2.loli.net/2022/11/20/oM3DWE5nYg9Gky1.png)

## fish(History存储的command-line)

![image-20221120201126673](https://s2.loli.net/2022/11/20/3PG6VL4XSpqb1hz.png)

## tree(or broot nnn)(文件树)

![image-20221120201107434](https://s2.loli.net/2022/11/20/u9JIPvXUfKjpdm3.png)

- 

## sed(脚本编写方式读文本)

```shell
#sed

cat file.sh | sed 's/REGEX/SUBSTITUTION/'
# 其中 REGEX 部分是我们需要使用的正则表达式，而 SUBSTITUTION 是用于替换匹配结果的文本。
```

![image-20221122222441207](https://s2.loli.net/2022/11/22/rAY4eyK8dtOUgkc.png)

## less(逐页滚动读文本)

```shell
# less

less [filename]
```

![image-20221122222756073](https://s2.loli.net/2022/11/22/u5U1ImSTP4xd2Hj.png)

## perl(文本处理工具[正则])

![image-20221123204439531](https://s2.loli.net/2022/11/23/GN2rCPIp8VkFfDg.png)

## wc(文本统计工具)

![image-20221123210116651](https://s2.loli.net/2022/11/23/PKXk3jwEgvJfDQA.png)

## awk(适合处理文本的编程语言)

![image-20221123211814842](https://s2.loli.net/2022/11/23/Iugqzd7Pb4hiaKC.png)

## bc(计算器语言)

![image-20221123213912118](https://s2.loli.net/2022/11/23/UNDIPkxln9Tu4LC.png)

## xargs(将标准输入作为参数)

## kill[(终止进程)](https://blog.csdn.net/WJSZMD/article/details/89331751)

```shell
kill -STOP[-TERM/-QUIT/-INT] <PID>
# STOP 暂停进程
# INT (Ctrl+C) : 只对当前前台进程,和他的所在的进程组的每个进程都发送SIGINT信号,之后这些进程会执行信号处理程序再终止
# QUIT (Ctrl+\) : 
# TERM : 当前进程会收到信号,而其子进程不会收到.如果当前进程被kill(即收到SIGTERM),则其子进程的父进程将为init,即pid为1的进程(kill <PID>)
# KILL : 当前进程收到该信号,注意该信号时无法被捕获的,也就是说进程无法执行信号处理程序,会直接发送默认行为,也就是直接退出 (kill -9 <PID>)
# HUP : 终止进程(无法终止nohup进程)
```

> ```shell
> SIGHUP     终止进程     终端线路挂断
> SIGINT     终止进程     中断进程
> SIGQUIT   建立CORE文件终止进程，并且生成core文件
> SIGILL   建立CORE文件       非法指令
> SIGTRAP   建立CORE文件       跟踪自陷
> SIGBUS   建立CORE文件       总线错误
> SIGSEGV   建立CORE文件       段非法错误
> SIGFPE   建立CORE文件       浮点异常
> SIGIOT   建立CORE文件       执行I/O自陷
> SIGKILL   终止进程     杀死进程
> SIGPIPE   终止进程     向一个没有读进程的管道写数据
> SIGALARM   终止进程     计时器到时
> SIGTERM   终止进程     软件终止信号
> SIGSTOP   停止进程     非终端来的停止信号
> SIGTSTP   停止进程     终端来的停止信号
> SIGCONT   忽略信号     继续执行一个停止的进程
> SIGURG   忽略信号     I/O紧急信号
> SIGIO     忽略信号     描述符上可以进行I/O
> SIGCHLD   忽略信号     当子进程停止或退出时通知父进程
> SIGTTOU   停止进程     后台进程写终端
> SIGTTIN   停止进程     后台进程读终端
> SIGXGPU   终止进程     CPU时限超时
> SIGXFSZ   终止进程     文件长度过长
> SIGWINCH   忽略信号     窗口大小发生变化
> SIGPROF   终止进程     统计分布图用计时器到时
> SIGUSR1   终止进程     用户定义信号1
> SIGUSR2   终止进程     用户定义信号2
> SIGVTALRM 终止进程     虚拟计时器到时
> ```

### pkill

> Mostly used for stopping processes.

```shell
# Kill all processes which match their full command instead of just the process name
pkill -f 'filename'
```

### wait

> Wait for a process to complete before proceeding.

![image-20221127203916231](https://s2.loli.net/2022/11/27/Nz14KJksgQxqAWY.png)

## tmux(终端多路复用)[^2]

![image-20221124221659078](https://s2.loli.net/2022/11/24/1qT2nSRvPBL74wM.png)

# Lecture 1: 课程概览与 shell

```shell
local:~$ echo "hello world"
local:~$ echo hello\ world

-> hello world
```

```shell
# 查询环境变量"PATH"
missing:~$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin

# 输出命令"echo"的执行路径
missing:~$ which echo
/bin/echo


missing:~$ /bin/echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
```

```shell
# print working directory
missing:~$ pwd
/home/missing

# change directory
missing:~$ cd /home
missing:/home$ pwd
/home

# . : current directory
# .. : the parent directory
missing:/home$ cd ..
missing:/$ pwd
/

# ~ : home directory
missing:/home$ cd ~
missing:/home$ pwd
/home

# - : previous directory(前一路径)
```

```shell
# ls -l(= ll)
usr:$ ll
total 30477
-rw-r--r-- 1 BAY 197610    19732 Nov  2 09:54 22_11_05组会.docx
-rw-r--r-- 1 BAY 197610      282 Nov  3 19:19 desktop.ini
-rw-r--r-- 1 BAY 197610     3003 Oct 28 10:13 environment.yml
-rw-r--r-- 1 BAY 197610 31182837 Nov  2 17:14 白澳阳22.11.05组会.pptx
# 九个字符，每三个字符构成一组。 （rwx）. 它们分别代表了文件所有者（missing），用户组（users） 以及其他所有人具有的权限
# w-修改 x-可执行 r-读
#  /bin 目录下的程序在最后一组，即表示所有人的用户组中，均包含 x 权限，也就是说任何人都可以执行这些程序。

# mv-重命名或移动 cp-拷贝 mkdir-新建文件夹

# 将程序的文档(用户手册)展现
usr:$ man ls
```

```sh
# 最简单的重定向是 < file 和 > file。这两个命令可以将程序的输入输出流分别重定向到文件：
missing:~$ echo hello > hello.txt
missing:~$ cat hello.txt
hello
missing:~$ cat < hello.txt > hello2.txt

# 您还可以使用 >> 来向一个文件追加内容。
missing:~$ echo hi >> hello2.txt

# | 操作符允许我们将一个程序的输出和另外一个程序的输入连接起来
# 使用管道（ pipes ），我们能够更好的利用文件重定向。
missing:~$ ls -l / | tail -n1
drwxr-xr-x 1 root  root  4096 Jun 20  2019 var
missing:~$ curl --head --silent google.com | grep --ignore-case content-length | cut --delimiter=' ' -f2
219
```

```shell
# 查找文件名中类似…h…的文件
find -name "*h*"

# tee: 输入并显示在屏幕上
echo hello | tee hello.txt
```

## chmod

| who  | 用户类型 | 说明                   |
| :--- | :------- | :--------------------- |
| `u`  | user     | 文件所有者             |
| `g`  | group    | 文件所有者所在组       |
| `o`  | others   | 所有其他用户           |
| `a`  | all      | 所有用户, 相当于 *ugo* |

| Operator | 说明                                                   |
| :------- | :----------------------------------------------------- |
| `+`      | 为指定的用户类型增加权限                               |
| `-`      | 去除指定用户类型的权限                                 |
| `=`      | 设置指定用户权限的设置，即将用户类型的所有权限重新设置 |

| 模式 | 名字         | 说明                                                         |
| :--- | :----------- | :----------------------------------------------------------- |
| `r`  | 读           | 设置为可读权限                                               |
| `w`  | 写           | 设置为可写权限                                               |
| `x`  | 执行权限     | 设置为可执行权限                                             |
| `X`  | 特殊执行权限 | 只有当文件为目录文件，或者其他类型的用户有可执行权限时，才将文件权限设置可执行 |
| `s`  | setuid/gid   | 当文件被执行时，根据who参数指定的用户类型设置文件的setuid或者setgid权限 |
| `t`  | 粘贴位       | 设置粘贴位，只有超级用户可以设置该位，只有文件所有者u可以使用该位 |

| #    | 权限           | rwx  | 二进制 |
| :--- | :------------- | :--- | :----- |
| 7    | 读 + 写 + 执行 | rwx  | 111    |
| 6    | 读 + 写        | rw-  | 110    |
| 5    | 读 + 执行      | r-x  | 101    |
| 4    | 只读           | r--  | 100    |
| 3    | 写 + 执行      | -wx  | 011    |
| 2    | 只写           | -w-  | 010    |
| 1    | 只执行         | --x  | 001    |
| 0    | 无             | ---  | 000    |

例如， 765 将这样解释：

- 所有者的权限用数字表达：属主的那三个权限位的数字加起来的总和。如 rwx ，也就是 4+2+1 ，应该是 7。
- 用户组的权限用数字表达：属组的那个权限位数字的相加的总和。如 rw- ，也就是 4+2+0 ，应该是 6。
- 其它用户的权限数字表达：其它用户权限位的数字相加的总和。如 r-x ，也就是 4+0+1 ，应该是 5。

# Lecture 2: Shell 工具和脚本

```shell
# 以'定义的字符串为原义字符串，其中的变量不会被转义，而 "定义的字符串会将变量值进行替换。
foo=bar
echo "$foo"
# 打印 bar
echo '$foo'
# 打印 $foo
```

```shell
$ vim mcd.sh
>
mcd () {
    mkdir -p "$1"
    cd "$1"
}
<

soucrce mcd.sh
mcd test
```



- `$0` - 脚本名
- `$1` 到 `$9` - 脚本的参数。 `$1` 是第一个参数，依此类推。
- `$@` - 所有参数
- `$#` - 参数个数
- `$?` - 前一个命令的返回值
- `$$` - 当前脚本的进程识别码
- `!!` - 完整的上一条命令，包括参数。常见应用：当你因为权限不足执行命令失败时，可以使用 `sudo !!`再尝试一次。
- `$_` - 上一条命令的最后一个参数。如果你正在使用的是交互式 shell，你可以通过按下 `Esc` 之后键入 . 来获取这个值。

```shell
#!/bin/bash

echo "Starting program at $(date)" # date会被替换成日期和时间

echo "Running program $0 with $# arguments with pid $$"

for file in "$@"; do
    grep foobar "$file" > /dev/null 2> /dev/null
    # > 代表标准输出流
    # 2> 代表标准错误
    # 如果模式没有找到，则grep退出状态为 1
    # 我们将标准输出流和标准错误流重定向到Null，因为我们并不关心这些信息
    if [[ $? -ne 0 ]]; then
        echo "File $file does not have any foobar, adding one"
        echo "# foobar" >> "$file"
    fi
done
```

```shell
convert image.{png,jpg}
# 会展开为
convert image.png image.jpg

cp /path/to/project/{foo,bar,baz}.sh /newpath
# 会展开为
cp /path/to/project/foo.sh /path/to/project/bar.sh /path/to/project/baz.sh /newpath

# 也可以结合通配使用
mv *{.py,.sh} folder
# 会移动所有 *.py 和 *.sh 文件

mkdir foo bar

# 下面命令会创建foo/a, foo/b, ... foo/h, bar/a, bar/b, ... bar/h这些文件
touch {foo,bar}/{a..h}
touch foo/x bar/y
# 比较文件夹 foo 和 bar 中包含文件的不同
diff <(ls foo) <(ls bar)
# 输出
# < x
# ---
# > y
```

```shell
#！/usr/bin/env python3
#!/usr/bin/python3
import sys
for arg in reversed(sys.argv[1:]):
    print(arg)
```

[^1]: ![shellcheck](https://s2.loli.net/2022/11/18/NZmjEu4ReKh9psF.png)



```shell
# find

# 查找所有名称为src的文件夹
find . -name src -type d
# 查找所有文件夹路径中包含test的python文件
find . -path '*/test/*.py' -type f
# 查找前一天修改的所有文件
find . -mtime -1
# 查找所有大小在500k至10M的tar.gz文件
find . -size +500k -size -10M -name '*.tar.gz'
```

```shell
#grep
# grep 有很多选项，这也使它成为一个非常全能的工具。其中我经常使用的有 -C ：获取查找结果的上下文（Context）；-v 将对结果进行反选（Invert），也就是输出不匹配的结果。举例来说， grep -C 5 会输出匹配结果前后五行。当需要搜索大量文件的时候，使用 -R 会递归地进入子目录并搜索所有的文本文件。

grep foobar mcd.sh
> # foobar

grep -R foobar
>
mcd.sh:# foobar
example.sh:	grep foobar "$file" > /dev/null 2> /dev/null
example.sh:		echo "File $file dose not have any foobar, adding one"
example.sh:		echo "# foobar" >> "$file"

# rg
# 查找所有使用了 requests 库的文件
rg -t py 'import requests'
# 查找所有没有写 shebang 的文件（包含隐藏文件）
rg -u --files-without-match "^#!"
# 查找所有的foo字符串，并打印其之后的5行
rg foo -A 5
# 打印匹配的统计信息（匹配的行和文件的数量）
rg --stats PATTERN
```

```shell
# history

history | grep find

# Ctrl+R
# 敲 Ctrl+R 后您可以输入子串来进行匹配，查找历史命令行。
# 反复按下就会在所有搜索结果中循环。
```

# Lecture 3: Vim

## 操作模式

Vim 的设计以大多数时间都花在阅读、浏览和进行少量编辑改动为基础，因此它具有多种操作模式：

- **正常模式**：在文件中四处移动光标进行修改(Esc)

- **插入模式**：插入文本(I)

- **替换模式**：替换文本(R)

- **可视化模式**（一般，行，块）：选中文本块(v-V-^v)

- **命令模式**：用于执行命令(:)

  - `:q` 退出（关闭窗口）

    - `:qa` 关闭所有窗口

  - `:w` 保存（写）

  - `:wq` 保存然后退出

  - `:e {文件名}` 打开要编辑的文件

  - `:ls` 显示打开的缓存

  - `:help` {标题}

    - 打开帮助文档

    `:help :w` 打开 `:w` 命令的帮助文档

    `:help w` 打开 `w` 移动的帮助文档

## 标签

`vim [filename]` 打开文件

`vim [num] [filename]` 打开文件，定位到第n行

`:sp`(`:split`)  竖直打开多个文件

`:vsp`(`:vsplit`) 水平打开多个文件

`:tabc` 关闭当前标签页

1. 文件间切换

`Ctrl+^` 下一个文件

`:bn` 下一个文件

`:bp` 上一个文件

2. 窗格间切换

`Ctrl+w+[上下左右]` 切换到前／下／上／后一个窗格 

`Ctrl+w+[hjkl]` 切换到前／下／上／后一个窗格 

`Ctrl+ww` 依次切换到下一个窗格中

3. 多文档便捷

`:n`  编辑下一个文档。 
`:2n` 编辑下两个文档。 
`:N` 编辑上一个文档。注意，该方法只能用于同时打开多个文档。 
`:e [filename]` 这是在进入vim后，不离开 vim 的情形下打开其他文档。 
`:e#` 或 `Ctrl+ˆ`   编辑上一个文档,用于两个文档相互交换编辑时使用。?# 代表的是编辑前一次编辑的文档 
`:files` 或` :buffers `或 `:ls`   可以列出目前 缓冲区 中的所有文档。加号 + 表示 缓冲区已经被修改过了。＃代表上一次编辑的文档，%是目前正在编辑中的文档 
`:b` 文档名或编号   移至该文档。 
`:f` 或 `Ctrl+g`   显示当前正在编辑的文档名称。 
`:f [filename]`     改变编辑中的文档名。(file)

## 移动

多数时候你会在正常模式下，使用移动命令在缓存中导航。在 Vim 里面移动也被称为 “名词”， 因为它们指向文字块。

- 基本移动: `hjkl` （左， 下， 上， 右）
- 词： `w` （下一个词）， `b` （词初）， `e` （词尾）
- 行： `0` （行初）， `^` （第一个非空格字符）， `$` （行尾）
- 屏幕： `H` （屏幕首行）， `M` （屏幕中间）， `L` （屏幕底部）
- 翻页（半页）： `Ctrl-u` （上翻半页）， `Ctrl-d` （下翻半页）
- 翻页一页：`Ctrl-f` （下翻一页）， `Ctrl-b`（上翻一页）
- 文件： `gg` （文件头）， `G` （文件尾）
- 行数： `:{行数}<CR>` 或者 `{行数}G` ({行数}为行数)
- 杂项： `%` （找到配对，比如括号或者 /* */ 之类的注释对）
- 查找：`f{字符}`，`t{字符}`，`F{字符}`，`T{字符}`

  -  查找/到 向前/向后 在本行的{字符}

  - `,` / `;` 用于导航匹配
- 搜索: `/{正则表达式}`, `n` / `N` 用于导航匹配

## 选择

可视化模式:

- 可视化：`v`
- 可视化行： `V`
- 可视化块：`Ctrl+v`

可以用移动命令来选中。

## 编辑

- `i`进入插入模式
  - 但是对于操纵/编辑文本，不单想用退格键完成
- `O` / `o` 在之上/之下插入行
- `d{移动命令}`删除 {移动命令}
  - 例如，`dw` 删除词, `d$` 删除到行尾, `d0` 删除到行头。
  - `dd` 删除当前行
  - `.` 重复删除操作
- `c{移动命令}`改变 {移动命令}
  - 例如，`cw` 改变词
  - 比如 `d{移动命令}` 再 `i`
- `x` 删除字符（等同于 `dl`）
- `r` 替换字符（`ra` 删除并替换成a）
- `s` 替换字符（删除并插入）（等同于 `xi`）
- 可视化模式 + 操作
  - 选中文字, `d` 删除 或者 `c` 改变
- `u` 撤销
-  `<C-r>` 重做
- `y` 复制 / “yank” （其他一些命令比如 `d` 也会复制）
- `p` 粘贴
- `.` 重复之前的编辑操作
- 更多值得学习的: 比如 `~` 改变字符的大小写
- `<<` `>>` 向左/右缩进当前行
- `==` 自动排版当前行
- `gg=G` 当前文档全文自动排版
- `<N>==` 对从当前行开始的 N 行进行自动排版
- `=<N>j` 对当前行以及向下 N 行进行自动排版
- `=<N>k` 对当前行以及向上 N 行进行自动排版

## 计数

你可以用一个计数来结合“名词”和“动词”，这会执行指定操作若干次。

- `3w` 向前移动三个词
- `5j` 向下移动5行
- `7dw` 删除7个词

## 修饰语

你可以用修饰语改变“名词”的意义。修饰语有 `i`，表示“内部”或者“在内“，和 `a`， 表示”周围“。

- `ci(` 改变当前括号内的内容
- `ci[` 改变当前方括号内的内容
- `da'` 删除一个单引号字符串， 包括周围的单引号

# Lecture 4: 数据整理

## 正则表达式

- `.` 除换行符之外的”任意单个字符”
- `*` 匹配前面字符零次或多次
- `+` 匹配前面字符一次或多次
- `[abc]` 匹配 `a`, `b` 和 `c` 中的任意一个
- `(RX1|RX2)` 任何能够匹配`RX1` 或 `RX2`的结果
- `^` 行首`$` 行尾

![image-20221123201541763](https://s2.loli.net/2022/11/23/WQe3h6AwMlVkPx5.png)

![image-20221123202123598](https://s2.loli.net/2022/11/23/wt3mpgHsAxRJL7U.png)

![image-20221123202317083](https://s2.loli.net/2022/11/23/4EPOI7V1cjDmXvT.png)

### 捕获组

```shell
# .*? 只搜索一次
# 一个括号进行一次捕获
# \1 第一次捕获的内容
echo 'abc ab a ab a abc ab a' | perl -pe 's/.*?ab(c| )/\1/'
c ab a ab a abc ab a
```

### 排序、过滤(sort|uniq -c)

![image-20221123210700038](https://s2.loli.net/2022/11/23/LxTZ38g6MWkzCdX.png)

```shell
# sort -nk1,1 : 仅基于以空格分割的第一列进行排序
# head ： 从前往后数
# tail ： 从后往前数
cat abc.md | perl -pe 's/.*?ab//' | sort | uniq -c | sort -nk1,1 | head -3
```

###  处理、同行(awk、paste)

```shell
# awk ‘{print $1}’ : 打印第一列
# paste -sd, : 大隐刀同一行并以，分割
cat abc.md | perl -pe 's/.*?ab//' | awk '{print $1}' | paste -sd,
>> c,c,c,c,a,a

```

### 计数(bc)

```shell
cat abc.md | perl -pe 's/.*?ab//' | sort | uniq -c | awk '{print $1}' | paste -sd+ | bc
```

![image-20221123214146853](https://s2.loli.net/2022/11/23/qMzZYsA94jIBdRv.png)

# Lecture5: 命令行环境

## 1.任务控制(Job control)

### 结束进程 

shell 会使用 UNIX 提供的信号机制执行进程间通信。当一个进程接收到信号时，它会停止执行、处理该信号并基于信号传递的信息来改变其执行。就这一点而言，信号是一种*软件中断*。

尽管 `SIGINT` 和 `SIGQUIT` 都常常用来发出和终止程序相关的请求。`SIGTERM` 则是一个更加通用的、也更加优雅地退出信号。为了发出这个信号我们需要使用 `kill`命令, 它的语法是： `kill -TERM <PID>`

`Ctrl+C` : `SIGINT`

`Ctrl+\ `: `SIGQUIT`

`Ctrl+Z` : `SIGTSTOP` (信号暂停)

`fg` / `bg` : 恢复暂停的job于前台/后台

![image-20221124213604608](https://s2.loli.net/2022/11/24/3NJIMat865OuZxD.png)

`jobs` : 列出当前终端会话中尚未完成的全部任务

`pgrep` : 模糊搜索任务的PID

`$!` : 最近的一个任务

![image-20221124213155164](https://s2.loli.net/2022/11/24/nzIBUcoF1hAdxMs.png)

#### kill

```shell
kill -TERM[-STOP/INT/QUIT] <PID>
```

## 2.终端多路复用(Terminal Multiplexe)

[^2]: tmux

### - sessions会话

- `tmux` 开始一个新的会话
- `tmux new -s NAME` 以指定名称开始一个新的会话
- `tmux ls` 列出当前所有会话
- 在 `tmux` 中输入 `<C-b> d` ，将当前会话分离
- `tmux a` 重新连接最后一个会话。您也可以通过 `-t` 来指定具体的会话

### - windows窗口

- `<C-b> c` 创建一个新的窗口，使用 `<C-d>`关闭
- `<C-b> N` 跳转到第 *N* 个窗口，注意每个窗口都是有编号的
- `<C-b> p` 切换到前一个窗口
- `<C-b> n` 切换到下一个窗口
- `<C-b> ,` 重命名当前窗口
- `<C-b> w` 列出当前所有窗口

### - panes面板

- `<C-b> "` 水平分割
- `<C-b> %` 垂直分割
- `<C-b> <方向>` 切换到指定方向的面板，<方向> 指的是键盘上的方向键
- `<C-b> z` 切换当前面板的缩放
- `<C-b> [` 开始往回卷动屏幕。您可以按下`<C-space>`来开始选择(上下左右)，`<Alt+w`复制选中的部分；`<C-b> }` 粘贴
- `<C-b> <空格>` 在不同的面板排布间切换
- `<C-b> <C-arrow>` 界面移动1行
- `<C-b> <Alt-arrow>` 界面移动5行
- `<C-b> x` 销毁面板
- `<C-b> t` 显示时钟

## 3.别名

`alias alias_name="command_to_alias arg1 arg2"`

```shell
 创建常用命令的缩写
alias ll="ls -lh"

# 能够少输入很多
alias gs="git status"
alias gc="git commit"
alias v="vim"

# 手误打错命令也没关系
alias sl=ls

# 重新定义一些命令行的默认行为
alias mv="mv -i"           # -i prompts before overwrite
alias mkdir="mkdir -p"     # -p make parent dirs as needed
alias df="df -h"           # -h prints human readable format

# 别名可以组合使用
alias la="ls -A"
alias lla="la -l"

# 在忽略某个别名
\ls
# 或者禁用别名
unalias la

# 获取别名的定义
alias ll
# 会打印 ll='ls -lh'
```

## 4.远程连接

### 生成秘钥

`ssh-keygen -o -a 100 -t ed25519 -f ~/.ssh/id_ed25519`

验证: `ssh-keygen -y -f /path/to/key`

### 基于秘钥的认证机制

`cat .ssh/id_ed25519 | ssh foobar@remote 'cat >> ~/.ssh/authorized_keys'`

或者

`ssh-copy-id -i .ssh/id_ed25519.pub foobar@remote`

### 复制文件

- `ssh+tee`, 最简单的方法是执行 `ssh` 命令，然后通过这样的方法利用标准输入实现 `cat localfile | ssh remote_server tee serverfile`。回忆一下，[`tee`](https://www.man7.org/linux/man-pages/man1/tee.1.html) 命令会将标准输出写入到一个文件；
- [`scp`](https://www.man7.org/linux/man-pages/man1/scp.1.html) ：当需要拷贝大量的文件或目录时，使用`scp` 命令则更加方便，因为它可以方便的遍历相关路径。语法如下：`scp path/to/local_file remote_host:path/to/remote_file`；
- [`rsync`](https://www.man7.org/linux/man-pages/man1/rsync.1.html) 对 `scp` 进行了改进，它可以检测本地和远端的文件以防止重复拷贝。它还可以提供一些诸如符号连接、权限管理等精心打磨的功能。甚至还可以基于 `--partial`标记实现断点续传。`rsync` 的语法和`scp`类似；

### SSH配置

`~/.ssh/config`

```shell
Host vm
    User foobar
    HostName 172.16.174.141
    Port 2222
    IdentityFile ~/.ssh/id_ed25519
    LocalForward 9999 localhost:8888

# 在配置文件中也可以使用通配符
Host *.mit.edu
    User foobaz
```

其他配置

```shell
bash - ~/.bashrc, ~/.bash_profile
git - ~/.gitconfig
vim - ~/.vimrc 和 ~/.vim 目录
ssh - ~/.ssh/config
tmux - ~/.tmux.conf
```

# Lecture6: 版本控制(Git)

- 更改全局的`branch_name` : `git config --global init.defaultBranch <name>`
- 更改当前仓库的分支名: `git branch -m <name>`

## 操作

### 基础

- `git help <command>`: 获取 git 命令的帮助信息
- `git init`: 创建一个新的 git 仓库，其数据会存放在一个名为 `.git` 的目录下
- `git status`: 显示当前的仓库状态
- `git add <filename>`: 添加文件到暂存区
- `git commit`: 创建一个新的提交
  - 如何编写 [良好的提交信息](https://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html)!
  - 为何要 [编写良好的提交信息](https://chris.beams.io/posts/git-commit/)
- `git log`: 显示历史日志
- `git log --all --graph --decorate`: 可视化历史记录（有向无环图）
- `git diff <filename>`: 显示与暂存区文件的差异
- `git diff <revision> <filename>`: 显示某个文件两个版本之间的差异
- `git checkout <revision>`: 更新 HEAD 和目前的分支

### 分支和合并

- `git branch`: 显示分支
- `git branch <name>`: 创建分支
- `git checkout -b <name>`: 创建分支并切换到该分支
  - 相当于 `git branch <name>; git checkout <name>`
- `git merge <revision>`: 合并到当前分支
- `git mergetool`: 使用工具来处理合并冲突
- `git rebase`: 将一系列补丁变基（rebase）为新的基线

### 远端操作

- `git remote`: 列出远端
- `git remote add <name> <url>`: 添加一个远端
- `git push <remote> <local branch>:<remote branch>`: 将对象传送至远端并更新远端引用
- `git branch --set-upstream-to=<remote>/<remote branch>`: 创建本地和远端分支的关联关系
- `git fetch`: 从远端获取对象/索引
- `git pull`: 相当于 `git fetch; git merge`
- `git clone`: 从远端下载仓库

### 撤销

- `git commit --amend`: 编辑提交的内容或信息
- `git reset HEAD <file>`: 恢复暂存的文件
- `git checkout -- <file>`: 丢弃修改
- `git restore`: git2.32版本后取代git reset 进行许多撤销操作

### Git 高级操作

- `git config`: Git 是一个 [高度可定制的](https://git-scm.com/docs/git-config) 工具
- `git clone --depth=1`: 浅克隆（shallow clone），不包括完整的版本历史信息
- `git add -p`: 交互式暂存
- `git rebase -i`: 交互式变基
- `git blame`: 查看最后修改某行的人
- `git stash`: 暂时移除工作目录下的修改内容
- `git bisect`: 通过二分查找搜索历史记录
- `.gitignore`: [指定](https://git-scm.com/docs/gitignore) 故意不追踪的文件

# Lecture7: 调试及性能分析

## 调试代码

### 打印调试法与日志

### 第三方日志系统

`journalctl --since "1m ago"`

### 调试器

**ipdb**

- install: `pip install ipdb`
- start: `python -m ipdb file.py`
- use: 
  - **l**(ist) - 显示当前行附近的11行或继续执行之前的显示；
  - **s**(tep) - 执行当前行，并在第一个可能的地方停止；
  - **n**(ext) - 继续执行直到当前函数的下一条语句或者 return 语句；
  - **b**(reak) - 设置断点（基于传入的参数）；
  - **p**(rint) - 在当前上下文对表达式求值并打印结果。还有一个命令是**pp** ，它使用 [`pprint`](https://docs.python.org/3/library/pprint.html) 打印；
    - `p [arr_name]` - 返回指定参数的值
    - `p locals()` - 返回所有值得字典
  - **r**(eturn) - 继续执行直到当前函数返回；
  - **q**(uit) - 退出调试器。
  - **c**(ontinue) - 继续执行
  - **Enter** - 重复上一条命令

**更底层的编程语言的调试器**：`gdb`

### 专门工具

系统调用: `strace`

网络数据包: `tcpdump` `Wireshark`

web

### 静态分析

#### 静态分析

**pyflakes**

![image-20221202232923986](https://s2.loli.net/2022/12/02/X3FiHqrGxTm27RU.png)

**mypy** : 可以对代码进行类型检查

![image-20221202233033197](https://s2.loli.net/2022/12/02/gibZHwRtGnqCM1o.png)

shell - **shellcheck**

文本 - **writegood**

#### code linting

**ale**

在 vim 中，有 [`ale`](https://vimawesome.com/plugin/ale) 或 [`syntastic`](https://vimawesome.com/plugin/syntastic) 可以帮助您做同样的事情。 在 Python 中， [`pylint`](https://www.pylint.org/) 和 [`pep8`](https://pypi.org/project/pep8/) 是两种用于进行风格检查的工具，而 [`bandit`](https://pypi.org/project/bandit/) 工具则用于检查安全相关的问题。

- 静态分析工具可以参考这个列表：[Awesome Static Analysis](https://github.com/mre/awesome-static-analysis) (您也许会对 *Writing* 一节感兴趣) 
- 对于 linters 则可以参考这个列表： [Awesome Linters](https://github.com/caramelomartins/awesome-linters)。

#### 自动格式化

**black**

用于 Python 的 [`black`](https://github.com/psf/black)、用于 Go 语言的 `gofmt`、用于 Rust 的 `rustfmt` 或是用于 JavaScript, HTML 和 CSS 的 [`prettier`](https://prettier.io/)

## 性能分析

### 计时

- `print(time.time() - start)`
- `time [do]`

### 性能分析工具（profilers）

#### cpu

- 追踪分析器(tracing): 记录程序的每一次函数调用
- 采样分析器(sampling): 周期性的监测（通常为每毫秒）您的程序并记录程序堆栈

- cProfile: 显示每次函数调用的时间
  - `python -m cProfile -s tottime grep.py 1000 '^(import|\s*def)[^,]*$' *.py`
- line_profiler: 基于行来显示时间
  - `kernprof -l -v a.py`

#### 内存

- **C/C++**: [Valgrind](https://valgrind.org/) 
- **python**:  [memory-profiler](https://pypi.org/project/memory-profiler/) 
  - `python -m memory_profiler example.py`

## 事件分析

**perf**

- `perf list` - 列出可以被 pref 追踪的事件；
- `perf stat COMMAND ARG1 ARG2` - 收集与某个进程或指令相关的事件；
- `perf record COMMAND ARG1 ARG2` - 记录命令执行的采样信息并将统计数据储存在`perf.data`中；
- `perf report` - 格式化并打印 `perf.data` 中的数据。

## 资源监控

- **通用监控** - 最流行的工具要数 [`htop`](https://htop.dev/),了，它是 [`top`](http://man7.org/linux/man-pages/man1/top.1.html)的改进版。`htop` 可以显示当前运行进程的多种统计信息。`htop` 有很多选项和快捷键，常见的有：`<F6>` 进程排序、 `t` 显示树状结构和 `h` 打开或折叠线程。 还可以留意一下 [`glances`](https://nicolargo.github.io/glances/) ，它的实现类似但是用户界面更好。如果需要合并测量全部的进程， [`dstat`](http://dag.wiee.rs/home-made/dstat/) 是也是一个非常好用的工具，它可以实时地计算不同子系统资源的度量数据，例如 I/O、网络、 CPU 利用率、上下文切换等等；
- **I/O 操作** - [`iotop`](http://man7.org/linux/man-pages/man8/iotop.8.html) 可以显示实时 I/O 占用信息而且可以非常方便地检查某个进程是否正在执行大量的磁盘读写操作；
- **磁盘使用** - [`df`](http://man7.org/linux/man-pages/man1/df.1.html) 可以显示每个分区的信息，而 [`du`](http://man7.org/linux/man-pages/man1/du.1.html) 则可以显示当前目录下每个文件的磁盘使用情况（ **d**isk **u**sage）。`-h` 选项可以使命令以对人类（**h**uman）更加友好的格式显示数据；[`ncdu`](https://dev.yorhel.nl/ncdu)是一个交互性更好的 `du` ，它可以让您在不同目录下导航、删除文件和文件夹；
- **内存使用** - [`free`](http://man7.org/linux/man-pages/man1/free.1.html) 可以显示系统当前空闲的内存。内存也可以使用 `htop` 这样的工具来显示；
- **打开文件** - [`lsof`](http://man7.org/linux/man-pages/man8/lsof.8.html) 可以列出被进程打开的文件信息。 当我们需要查看某个文件是被哪个进程打开的时候，这个命令非常有用；
- **网络连接和配置** - [`ss`](http://man7.org/linux/man-pages/man8/ss.8.html) 能帮助我们监控网络包的收发情况以及网络接口的显示信息。`ss` 常见的一个使用场景是找到端口被进程占用的信息。如果要显示路由、网络设备和接口信息，您可以使用 [`ip`](http://man7.org/linux/man-pages/man8/ip.8.html) 命令。注意，`netstat` 和 `ifconfig` 这两个命令已经被前面那些工具所代替了。
- **网络使用** - [`nethogs`](https://github.com/raboof/nethogs) 和 [`iftop`](http://www.ex-parrot.com/pdw/iftop/) 是非常好的用于对网络占用进行监控的交互式命令行工具。

### 专用工具

**hyperfine**

# Lecture8: 元编程(Metaprogramming)*

# Lecture9: 安全和密码学(Security and Cryptography)*

## 熵(Entropy)

熵的单位是 *比特* = $log_2{probility}$

## 散列函数

`hash(value: array<byte>) -> vector<byte, N>  (N对于该函数固定)`

### 特性

- 确定性：对于不变的输入永远有相同的输出。
- 不可逆性：对于`hash(m) = h`，难以通过已知的输出`h`来计算出原始输入`m`。
- 目标碰撞抵抗性/弱无碰撞：对于一个给定输入`m_1`，难以找到`m_2 != m_1`且`hash(m_1) = hash(m_2)`。
- 碰撞抵抗性/强无碰撞：难以找到一组满足`hash(m_1) = hash(m_2)`的输入`m_1, m_2`（该性质严格强于目标碰撞抵抗性）。

# Lecture10: 大杂烩(Potpourri)

## 修改键位映射

- macOS - [karabiner-elements](https://pqrs.org/osx/karabiner/), [skhd](https://github.com/koekeishiya/skhd) 或者 [BetterTouchTool](https://folivora.ai/)
- Linux - [xmodmap](https://wiki.archlinux.org/index.php/Xmodmap) 或者 [Autokey](https://github.com/autokey/autokey)
- Windows - 控制面板，[AutoHotkey](https://www.autohotkey.com/) 或者 [SharpKeys](https://www.randyrants.com/category/sharpkeys/)
- QMK - 如果你的键盘支持定制固件，[QMK](https://docs.qmk.fm/) 可以直接在键盘的硬件上修改键位映射。保留在键盘里的映射免除了在别的机器上的重复配置。

## 守护进程（daemon）

查看所有守护进程: `systemctl status`
