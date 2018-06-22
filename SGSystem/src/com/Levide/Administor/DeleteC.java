package com.Levide.Administor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import com.example.DAO.CourseDao;
import com.example.bean.Course;

public class DeleteC implements ActionListener {

	JFrame frame;
	CourseDao cd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteC window = new DeleteC();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cd = new CourseDao();
		frame = new JFrame();
		frame.setTitle("删除指定课程信息");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JLabel label = new JLabel("删除指定课程信息");
		label.setFont(new Font("华文楷体", Font.BOLD, 30));
		label.setBounds(71, 10, 272, 44);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("请输入课程号：");
		label_1.setFont(new Font("华文楷体", Font.PLAIN, 18));
		label_1.setBounds(57, 87, 126, 23);
		frame.getContentPane().add(label_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(179, 88, 164, 22);
		frame.getContentPane().add(textArea);
		
		JButton button = new JButton("删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Cno = textArea.getText();
				String cno = textArea.getText();
				Course c = cd.getCourseByCno(cno);
				if(c==null){
					JOptionPane.showMessageDialog(null, "没有该课程");
				}else{
				
				Connection conn = null;	
				conn = ConnectionFactory.getInstance().makeConnection();
				try {
					
					conn.setAutoCommit(false);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				try {
					PreparedStatement ps = conn.prepareStatement("delete from course where Cno = ?");				
					ps.setString(1, Cno);
					ps.executeUpdate();
					conn.commit();
					JOptionPane.showMessageDialog(frame,"删除成功");
				} catch (SQLException e1) {
					try {
						conn.rollback();
						JOptionPane.showMessageDialog(null, "请检查所输入的数据", "删除错误", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					e1.printStackTrace();
				}
				}
			}
		});
		button.setBounds(79, 174, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(this);
		button_1.setBounds(232, 174, 93, 23);
		frame.getContentPane().add(button_1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("退出")){
			new AllFunction().frame.setVisible(true);
			this.frame.dispose();
		}
		
	}
}
