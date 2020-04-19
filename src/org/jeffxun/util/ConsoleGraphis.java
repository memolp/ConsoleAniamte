package org.jeffxun.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.TimerTask;

public class ConsoleGraphis extends TimerTask
{
	// 缓存列表的元素最大数量
	protected static final int MAX_LIST_SIZE = 50;
	// 最大行数
	protected static final int MAX_ROW_SIZE = 10;
	// 表现效果
	protected GraphisType mEffectType = GraphisType.SWIM;
	// 缓存数据
	protected LinkedList<Integer> mValues = new LinkedList<Integer>();
	// 随机
	protected Random mRandom = new Random();
	// 是否显示空方块
	protected boolean mBackShow = true;
	// 用于生成sin曲线值
	protected double mAngleValue = 0.0;
	/**
	 * 构造
	 */
	public ConsoleGraphis() 
	{
		this.InitData();
	}
	/**
	 * 设置是否显示空方块
	 * @param v
	 */
	public void SetBackDisplay(boolean v)
	{
		mBackShow = v;
	}
	/**
	 * 设置渲染效果
	 * @param v
	 */
	public void SetEffectType(GraphisType v)
	{
		mEffectType = v;
	}
	/**
	 * 数据初始化
	 */
	private void InitData()
	{
		//初始化先填入50个元素 
		for(int i = 0; i < MAX_LIST_SIZE; i++)
		{
			int v = this.randValue();
			mValues.add(v);
		}
	}
	/**
	 * 清除屏幕重绘
	 * @throws InterruptedException
	 * @throws IOException
	 */
	protected void clear()
	{
		// 为了省事暂时先放里面了
		try
		{
			//windows
			new ProcessBuilder("cmd","/c" , "cls").inheritIO().start().waitFor();
			//linux
			//new ProcessBuilder("clear").inheritIO().start().waitFor();
		}catch(Exception e)
		{
			
		}
	}
	/**
	 * 随机一个数，在最大值以内
	 * @return
	 */
	protected int randValue()
	{
		return Math.abs(mRandom.nextInt()) % MAX_ROW_SIZE + 1;
	}
	protected int sinValue()
	{
		mAngleValue += 0.05;
		return (int) Math.max(1, Math.sin(mAngleValue)*10);
	}
	/**
	 * 渲染效果
	 */
	protected void render()
	{
		// 按照
		for(int i = 0; i < MAX_ROW_SIZE; i++)
		{
			StringBuilder sb = new StringBuilder();
			for(Integer v : mValues)
			{	
				if(MAX_ROW_SIZE - v == i)
				{
					sb.append(ConsoleColors.RED_BOLD+"■"+ConsoleColors.RESET);
				}
				else if(MAX_ROW_SIZE - v < i)
				{
					sb.append(ConsoleColors.PURPLE_BOLD+"■"+ConsoleColors.RESET);
				}else
				{
					if(mBackShow)
						sb.append("□");
					else
						sb.append("　");
				}
			}
			System.out.println(sb.toString());
		}
	}
	/**
	 * 更新数据
	 */
	protected void update()
	{
		if(mEffectType == GraphisType.SWIM)
		{
			int v = this.randValue();
			mValues.remove(0);
			mValues.add(v);
		}else if(mEffectType == GraphisType.WAVE)
		{
			for(int i=0 ; i < MAX_LIST_SIZE; i++)
			{
				int v = this.randValue();
				mValues.set(i, v);
			}
		}
		
	}
	
	protected void display_usage()
	{
		
	}
	
	@Override
	public void run() 
	{
		//执行更新
		this.update();
		// 清除屏幕旧的绘制
		this.clear();
		System.out.println("波浪效果输入'w'");
		System.out.println("流动效果输入's'");
		System.out.println("隐藏网格输入'h'");
		System.out.println("显示网格输入'g'");
		System.out.println("退出程序输入'q'");
		//进行渲染
		this.render();
	}
}
