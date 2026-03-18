package com.GUI2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddFrame extends JFrame implements ActionListener {
    private JButton btnSave = new JButton("保存");
    private JButton btnCancel = new JButton("取消");
    private JTextField titleText = new JTextField();
    private JTextArea contentText = new JTextArea();
    public AddFrame(){
        initFrame();
        initView();
        setVisible(true);
    }

    private void initView() {
        JLabel lblTitle = new JLabel("每日一记");
        lblTitle.setBounds(220,20,584,50);
        lblTitle.setFont(new Font("宋体",Font.BOLD,32));
        getContentPane().add(lblTitle);

        JLabel lblSub = new JLabel("标题");
        lblSub.setBounds(70,90,100,30);
        lblSub.setFont(new Font("宋体",Font.PLAIN,16));
        getContentPane().add(lblSub);

        JLabel lblContent = new JLabel("内容");
        lblContent.setBounds(70,140,100,30);
        lblContent.setFont(new Font("宋体",Font.PLAIN,16));
        getContentPane().add(lblContent);

        titleText.setBounds(120,90,426,30);
        titleText.setFont(new Font("宋体",Font.PLAIN,16));
        getContentPane().add(titleText);

        contentText.setBounds(120,140,426,300);
        contentText.setFont(new Font("宋体",Font.PLAIN,16));
        getContentPane().add(contentText);

        btnSave.setBounds(132,466,140,40);
        btnSave.setFont(new Font("宋体",Font.PLAIN,24));
        btnSave.addActionListener(this);
        getContentPane().add(btnSave);

        btnCancel.setBounds(312,466,140,40);
        btnCancel.setFont(new Font("宋体",Font.PLAIN,24));
        btnCancel.addActionListener(this);
        getContentPane().add(btnCancel);

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
        if(obj == btnSave){
            //获得标题和内容文本
            String title = titleText.getText().trim();
            String content = contentText.getText();
            //判断两个字符串都不为空
            if(title.isEmpty() || content.isEmpty()){
                JOptionPane.showMessageDialog(AddFrame.this,"标题或内容不能为空！");
                return;
            }
            //写入指定的文件中
            File file = new File(title);
            //判断文件是否存在，如果不存在创建一个新文件
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            //将内容写入指定的文件中
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file));
                bw.write(content);
                JOptionPane.showMessageDialog(AddFrame.this,"日记保存成功");
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
            BufferedWriter bw2 = null;
            try {
                bw2 = new BufferedWriter(new FileWriter("list",true));
                bw2.write(title+"\n");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }finally{
                if(bw2!=null){
                    try {
                        bw2.close();
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
}
