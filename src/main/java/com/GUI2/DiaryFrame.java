package com.GUI2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class DiaryFrame extends JFrame implements ActionListener {
    JButton btnAdd = new JButton("添加");
    JButton btnUpdate = new JButton("修改");
    JButton btnDel = new JButton("删除");
    JDialog jDialog = new JDialog();
    JTable table = null;
    ArrayList<ListRecord> records = new ArrayList<>();
    DefaultTableModel dtm = null;
    ArrayList<String> ss = new ArrayList<>();
    String s,y;

    public DiaryFrame(){
        //加载数据
        loadData();
        //初始化界面
        initFrame();
        //初始化组件
        initView();
        setVisible(true );
    }

    private void loadData() {
        records.clear();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("list"));
            String str = null;
            ListRecord lr = null;
            int id = 1;
            while((str = br.readLine())!=null){
                lr = new ListRecord(id+"",str);
                records.add(lr);
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new DiaryFrame();
    }


    private void initFrame() {
        //设置界面的宽高
        this.setSize(600, 600);
        //设置界面的标题
        this.setTitle("每日一记");
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

    private void initView() {
        JLabel lblTitle = new JLabel("每日一记");
        lblTitle.setBounds(220,20,584,50);
        lblTitle.setFont(new Font("宋体",Font.BOLD,32));
        getContentPane().add(lblTitle);
        //表格标题
        dtm = new DefaultTableModel();
        //创建两列列名
        dtm.setColumnIdentifiers(new String[]{"编号","标题"});
        //添加数据行
        for(ListRecord lr:records){
            dtm.addRow(new String[]{lr.getId(),lr.getTitle()});
        }

        table = new JTable(dtm);
//        int selectedRow = table.getSelectedRow();//获取选中行的行号
//        dtm.removeRow(selectedRow);
//        dtm.fireTableDataChanged();//刷新表格

        //定义表格组件
//        JTable table = new JTable(dtm);
        table.setBounds(40,70,504,380);
        //创建滚动面板，并添加表格组件至其中
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40,70,504,380);
        getContentPane().add(scrollPane);
        //设置三个按钮的宽高属性，并添加点击事件
        btnAdd.setBounds(40,466,140,40);
        btnUpdate.setBounds(222,466,140,40);
        btnDel.setBounds(404,466,140,40);
        btnAdd.setFont(new Font("宋体",Font.PLAIN,24));
        btnUpdate.setFont(new Font("宋体",Font.PLAIN,24));
        btnDel.setFont(new Font("宋体",Font.PLAIN,24));
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDel.addActionListener(this);

        getContentPane().add(btnAdd);
        getContentPane().add(btnUpdate);
        getContentPane().add(btnDel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btnAdd){
//            setVisible(false);
            dispose();
            new AddFrame();
        } else if (obj == btnUpdate) {
            setVisible(false);
            int selectedRow = table.getSelectedRow();
            new UpdateFrame(selectedRow);
        }else if(obj == btnDel){
            showChooseDialog();
            int i ;
            int selectedRow = table.getSelectedRow(); //获取选中行的行号
            System.out.println(selectedRow);
            if(selectedRow>=0){
                dtm.removeRow(selectedRow);
                dtm.fireTableDataChanged();
            }
                BufferedReader bf = null;
                try {
                    bf = new BufferedReader(new FileReader("list"));

                    int a=0;
                    while((s = bf.readLine())!=null){
                        if(a!=selectedRow){
                            ss.add(s);
                        }else{
                            y=s;
                        }
                        a++;
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    if(bf!=null){
                        try {
                            bf.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
                File f = new File("list");
                File f2 = new File(y);
                if(f.exists()){
                    f.delete();
                }
                if(f2.exists()){
                    f2.delete();
                }
                BufferedWriter bw = null;
                int z=0;
            try {
                bw = new BufferedWriter(new FileWriter("list",true));
                while(z<ss.size()){
                    bw.write(ss.get(z)+"\n");
                    z++;
                }


            } catch (Exception Exception) {
                Exception.printStackTrace();
            } finally {
                if(bw!=null){
                    try {
                        bw.close();
                    } catch (Exception Exception) {
                        Exception.printStackTrace();
                    }
                }
            }


        }
    }

    private int showChooseDialog() {
        return JOptionPane.showConfirmDialog(this, "是否删除选中数据？","删除信息确认",JOptionPane.YES_NO_OPTION);

    }

    public void showJDialog(String content) {
        if (!jDialog.isVisible()) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //给弹框设置大小
            jDialog.setSize(200, 150);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭永远无法操作下面的界面
            jDialog.setModal(true);

            //创建Jlabel对象管理文字并添加到弹框当中
            JLabel warning = new JLabel(content);
            warning.setBounds(0, 0, 200, 150);
            jDialog.getContentPane().add(warning);

            //让弹框展示出来
            jDialog.setVisible(true);
        }
    }
}
