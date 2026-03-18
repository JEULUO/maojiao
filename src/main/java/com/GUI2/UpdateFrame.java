package com.GUI2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class UpdateFrame extends JFrame implements ActionListener {
    int i=0;
    JTextField titleText = new JTextField();
    JTextArea contentText = new JTextArea();
    JButton btnUpdate = new JButton("修改");
    JButton btnCancel = new JButton("取消");
    String s,y,v;
    int a = 0;

    public UpdateFrame(int i){
        initFrame();
        initView();
        setVisible(true);
        if(i>=0){
            this.i=i;
        }else{
            i=0;
        }
        init();
    }

    private void initView() {
        //定义最上面的每日一记
        JLabel title = new JLabel("每日一记");
        title.setBounds(220, 20, 584, 50);
        title.setFont(new Font("宋体", Font.BOLD, 32));
        this.getContentPane().add(title);

        //定义文字：标题
        JLabel subject = new JLabel("标题");
        subject.setBounds(70,90,100,30);
        subject.setFont(new Font("宋体",Font.PLAIN,16));
        this.getContentPane().add(subject);

        //定义文字：内容
        JLabel content = new JLabel("内容");
        content.setBounds(70,140,100,30);
        content.setFont(new Font("宋体",Font.PLAIN,16));
        this.getContentPane().add(content);


        //设置标题的输入框
        titleText.setBounds(120,90,426,30);
        titleText.setFont(new Font("宋体",Font.PLAIN,16));
        this.getContentPane().add(titleText);

        //设置内容的输入框
        contentText.setBounds(120,140,426,300);
        contentText.setFont(new Font("宋体",Font.PLAIN,16));
        this.getContentPane().add(contentText);

        //设置保存按钮
        btnUpdate.setBounds(132,466,140,40);
        btnUpdate.setFont(new Font("宋体",Font.PLAIN,24));
        btnUpdate.addActionListener(this);
        this.getContentPane().add(btnUpdate);

        //设置取消按钮
        btnCancel.setBounds(312,466,140,40);
        btnCancel.setFont(new Font("宋体",Font.PLAIN,24));
        btnCancel.addActionListener(this);
        this.getContentPane().add(btnCancel);
    }

    private void initFrame() {
        //设置界面的宽高
        this.setSize(600, 600);
        //设置界面的标题
        this.setTitle("每日一记（添加日记）");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //设置背景颜色
        this.getContentPane().setBackground(new Color(212,212,212));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        if (obj == btnUpdate){
            String title = titleText.getText().trim();
            String content = contentText.getText();
            //判断两个字符串都不为空
            if(title.isEmpty() || content.isEmpty()){
                JOptionPane.showMessageDialog(this,"标题或内容不能为空！");
                return;
            }
            //写入指定的文件中
            File file = new File(title);
            //将内容写入指定的文件中
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file));
                bw.write(content);
                JOptionPane.showMessageDialog(this,"日记修改成功");
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }finally{
                if(bw!=null){
                    try {
                        bw.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }else if(obj == btnCancel){
            dispose();
            new DiaryFrame();
        }
    }

    public void init(){
        titleText.setEditable(false);
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader("list"));
            while((s = bf.readLine())!=null){
                if(a==i){
                    break;
                }
                a++;
            }
        } catch (Exception Exception) {
            Exception.printStackTrace();
        } finally {
            try {
                if(bf!=null){
                    bf.close();
                }

            } catch (Exception Exception) {
                Exception.printStackTrace();
            }
        }
        File f = new File(s);
        BufferedReader bf2 = null;
        try {
            bf2 = new BufferedReader(new FileReader(f));
            char b[] = new char[(int) f.length()];
            int length = bf2.read(b);
            v = new String(b,0,length);
        } catch (Exception Exception) {
            Exception.printStackTrace();
        } finally {
            if(bf2!=null){
                try {
                    bf2.close();
                } catch (IOException Exception) {
                    Exception.printStackTrace();
                }
            }

        }

        titleText.setText(s);
        contentText.setText(v);
    }
}
