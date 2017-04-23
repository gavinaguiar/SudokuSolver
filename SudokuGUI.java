import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

public class SudokuGUI 
{
	JButton btnSolve,btnReset, btnEasy, btnMedium, btnHard;
	JTextField[][] num = new JTextField[9][9];
	JPanel mainPanel = new JPanel(new BorderLayout());
	Font font1 = new Font("Calibri", Font.HANGING_BASELINE, 30);
	int numArray[][] =  new int[9][9];
	
	
	JFrame frame;			

	public SudokuGUI()
	{
		frame = new JFrame();
		frame.setSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		grids();
		
		buttons();
		
		
	}
	
	public void buttons()
	{
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(btnPanel, BorderLayout.NORTH);
		
		JPanel panelOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelOptions.setBorder(BorderFactory.createTitledBorder(" Options "));
		btnPanel.add(panelOptions);
		
		
		btnSolve = new JButton("Solve");
		panelOptions.add(btnSolve);
		
		btnReset = new JButton("Reset");
		panelOptions.add(btnReset);
		
		JPanel panelNew = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelNew.setBorder(BorderFactory.createTitledBorder("New Game"));
		btnPanel.add(panelNew);
		
		btnEasy = new JButton("Easy");
		panelNew.add(btnEasy);
		
		btnMedium = new JButton("Medium");
		panelNew.add(btnMedium);
		
		btnHard = new JButton("Hard");
		panelNew.add(btnHard);
		
		
		btnSolve.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) 
			{
				for(int i=0;i<9;i++)
				{
					for (int j=0;j<9;j++)
					{
						if (num[i][j].getText().equals(""))
							num[i][j].setText("0");
						
						numArray[i][j] = Integer.parseInt(num[i][j].getText());
					}
				}
				
				Sudoku s = new Sudoku(numArray);
				
				numArray = s.grid;
				
				for(int i=0;i<9;i++)
				{
					for (int j=0;j<9;j++)
					{
						num[i][j].setText(""+numArray[i][j]);
					}
				}
			}
		});
		
		
		btnReset.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent arg0) 
			{
				reset();
			}
		});
		
		
		btnEasy.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) 
			{
				reset();
				int row = randomCellGenerator();
				int col = randomCellGenerator();
				int n = randomNumberGenerator();
				
				numArray[row][col] = n; 
				
				
				Sudoku s = new Sudoku(numArray);
				
				numArray = s.grid;
				
				for(int j=0;j<29;j++)
				{
					int r = randomCellGenerator();
					int c = randomCellGenerator();
					
					num[r][c].setText(""+numArray[r][c]);
					
					
				}
			}
		});
		
		
		btnMedium.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) 
			{
				reset();
				int row = randomCellGenerator();
				int col = randomCellGenerator();
				int n = randomNumberGenerator();
				
				numArray[row][col] = n; 
				
				
				Sudoku s =new Sudoku(numArray);
				
				numArray = s.grid;
				
				for(int j=0;j<25;j++)
				{
					int r = randomCellGenerator();
					int c = randomCellGenerator();
					
					num[r][c].setText(""+numArray[r][c]);
					
					
				}
			}
		});
		
		btnHard.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) 
			{
				reset();
				int row = randomCellGenerator();
				int col = randomCellGenerator();
				int n = randomNumberGenerator();
				
				numArray[row][col] = n; 
				
				
				Sudoku s = new Sudoku(numArray);
				
				numArray = s.grid;
				
				for(int j=0;j<21;j++)
				{
					int r = randomCellGenerator();
					int c = randomCellGenerator();
					
					num[r][c].setText(""+numArray[r][c]);
					
					
				}
			}
		});
	}
	
	
	public void reset()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				num[i][j].setText("");
				numArray[i][j] = 0;
			}
		}
	}
	
	public int randomCellGenerator()
	{
		int n =(int)( -1 + (Math.random() * (10)));
		return n;
	}
	
	public int randomNumberGenerator()
	{
		int n =(int)( 0 + (Math.random() * (10)));
		return n;
	}
	
	
	public void solve()
	{
		Sudoku s = new Sudoku(numArray);
		
		numArray = s.grid;
		
		for(int i=0;i<9;i++)
		{
			for (int j=0;j<9;j++)
			{
				num[i][j].setText(""+numArray[i][j]);
			}
		}
	}
	
	public void grids()
	{
		mainPanel.setLayout(new GridLayout(1, 2));
		frame.add(mainPanel);
		
		JPanel panel1 = new JPanel(new GridLayout(3, 3));
		panel1.setLayout(new GridLayout(3, 3));
		panel1.setSize(300, 300);
		mainPanel.add(panel1);

		
		JPanel[][] grid = new JPanel[3][3];

		int c1 = 0; // text field row selector.
		int temp; // column selector of 3*3 panel.
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				grid[i][j] = new JPanel(new GridLayout(3, 3));
				grid[i][j].setBorder(BorderFactory
						.createLineBorder(Color.black));
				panel1.add(grid[i][j]);
			}
		for (int i = 0; i < 3; i++) {
			switch (i) {
			case 0:
				c1 = 0;
				break;
			case 1:
				c1 = 3;
				break;
			case 2:
				c1 = 6;
				break;
			}

			for (int j = 0; j < 3; j++) {
				temp = 0;
				for (int k = 0; k < 9; k++) {
					if (k == 3)
						temp++; // jump to second panel
					else if (k == 6) {
						temp--; // temp=0
						temp = temp + 2; // jump to third panel
					}
					num[c1][k] = new JTextField(5);
					num[c1][k].setBorder(BorderFactory
							.createLineBorder(Color.GRAY));
					num[c1][k].setSize(5, 5);
					num[c1][k].setFont(font1);
					grid[i][temp].add(num[c1][k]);
				}
				c1++; // as j increments c1 must also be incremented
			}
		}
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				num[i][j].setFont(font1);
				num[i][j].setHorizontalAlignment(JTextField.CENTER);
			}
		
		
	}
	
	

	public static void main(String[] args) 
	{
		new SudokuGUI();
	}



}
