package com.fesco.bookpay.view.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义日历卡
 *
 * @author wuwenjie
 *
 */
public class CalendarCard extends View {

	private static final int TOTAL_COL = 7; // 7列
	private static final int TOTAL_ROW = 6; // 6行

	private Paint mCirclePaint; // 绘制圆形的画笔
	private Paint mCirclePaint2; // 绘制圆形的画笔
	private Paint mTextPaint; // 绘制文本的画笔
	private int mViewWidth; // 视图的宽度
	private int mViewHeight; // 视图的高度
	private int mCellSpace; // 单元格间距
	private Row rows[] = new Row[TOTAL_ROW]; // 行数组，每个元素代表一行
	private static CustomDate mShowDate; // 自定义的日期，包括year,month,day
	private OnCellClickListener mCellClickListener; // 单元格点击回调事件
	private int touchSlop; //
	private boolean callBackCellSpace;

	private Cell mClickCell;
	private float mDownX;
	private float mDownY;
	private List<List<String>>  lists=new ArrayList<List<String>>();
	/**
	 * 单元格点击的回调接口
	 *
	 * @author wuwenjie
	 *
	 */
	public interface OnCellClickListener {
		void clickDate(CustomDate date); // 回调点击的日期

		void changeDate(CustomDate date); // 回调滑动ViewPager改变的日期
	}

	public CalendarCard(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public CalendarCard(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CalendarCard(Context context) {
		super(context);
		init(context);
	}

	public CalendarCard(Context context, OnCellClickListener listener) {
		super(context);
		this.mCellClickListener = listener;
		init(context);

	}

	private void init(Context context) {
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mCirclePaint.setStyle(Paint.Style.FILL);
		mCirclePaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
		mCirclePaint2.setStyle(Paint.Style.FILL);
		//	mCirclePaint.setColor(Color.parseColor("#F24949")); // 红色圆形
		mTextPaint.setColor(Color.BLACK);
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

		initDate();
	}

	private void initDate() {
		mShowDate = new CustomDate();
		fillDate();//
	}

	private void fillDate() {
		int monthDay = DateUtil.getCurrentMonthDay(); // 今天
		int lastMonthDays = DateUtil.getMonthDays(mShowDate.year,
				mShowDate.month - 1); // 上个月的天数
		int currentMonthDays = DateUtil.getMonthDays(mShowDate.year,
				mShowDate.month); // 当前月的天数
		int firstDayWeek = DateUtil.getWeekDayFromDate(mShowDate.year,
				mShowDate.month);
		boolean isCurrentMonth = false;
		if (DateUtil.isCurrentMonth(mShowDate)) {
			isCurrentMonth = true;
		}
		int day = 0;
		for (int j = 0; j < TOTAL_ROW; j++) {
			rows[j] = new Row(j);
			for (int i = 0; i < TOTAL_COL; i++) {
				int position = i + j * TOTAL_COL; // 单元格位置
				// 这个月的
				if (position >= firstDayWeek
						&& position < firstDayWeek + currentMonthDays) {
					day++;
					rows[j].cells[i] = new Cell(CustomDate.modifiDayForObject(
							mShowDate, day), State.CURRENT_MONTH_DAY, i, j);
					// 今天
					if (isCurrentMonth && day == monthDay ) {
						CustomDate date = CustomDate.modifiDayForObject(mShowDate, day);
						rows[j].cells[i] = new Cell(date, State.TODAY, i, j);
					}

					if (isCurrentMonth && day > monthDay) { // 如果比这个月的今天要大，表示还没到
						rows[j].cells[i] = new Cell(
								CustomDate.modifiDayForObject(mShowDate, day),
								State.UNREACH_DAY, i, j);
					}
					// 过去一个月
				} else if (position < firstDayWeek) {
					rows[j].cells[i] = new Cell(new CustomDate(mShowDate.year,
							mShowDate.month - 1, lastMonthDays
							- (firstDayWeek - position - 1)),
							State.PAST_MONTH_DAY, i, j);

					// 下个月
				} else if (position >= firstDayWeek + currentMonthDays) {
					rows[j].cells[i] = new Cell((new CustomDate(mShowDate.year,
							mShowDate.month + 1, position - firstDayWeek
							- currentMonthDays + 1)),
							State.NEXT_MONTH_DAY, i, j);

				}
			}
		}
		mCellClickListener.changeDate(mShowDate);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < TOTAL_ROW; i++) {
			if (rows[i] != null) {
				rows[i].drawCells(canvas);
			}
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mViewWidth = w;
		mViewHeight =w;
		mCellSpace = Math.min(mViewHeight / TOTAL_ROW, mViewWidth / TOTAL_COL);
		if (!callBackCellSpace) {
			callBackCellSpace = true;
		}
		mTextPaint.setTextSize(mCellSpace / 4);
//		Log.e("Fragment","w: "+mViewWidth+" H: "+mViewHeight+"---mCellSpace "+mCellSpace);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mDownX = event.getX();
				mDownY = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				float disX = event.getX() - mDownX;
				float disY = event.getY() - mDownY;
				if (Math.abs(disX) < touchSlop && Math.abs(disY) < touchSlop) {
					int col = (int) (mDownX / mCellSpace);
					int row = (int) (mDownY / mCellSpace);
					measureClickCell(col, row);
				}
				break;
			default:
				break;
		}

		return true;
	}

	/**
	 * 计算点击的单元格
	 * @param col
	 * @param row
	 */
	private void measureClickCell(int col, int row) {
		if (col >= TOTAL_COL || row >= TOTAL_ROW)
			return;
		if (mClickCell != null) {
			rows[mClickCell.j].cells[mClickCell.i] = mClickCell;
		}
		if (rows[row] != null) {
			mClickCell = new Cell(rows[row].cells[col].date,
					rows[row].cells[col].state, rows[row].cells[col].i,
					rows[row].cells[col].j);

			CustomDate date = rows[row].cells[col].date;
			date.week = col;
			mCellClickListener.clickDate(date);

			// 刷新界面
			update();
		}
	}

	/**
	 * 组元素
	 *
	 * @author wuwenjie
	 *
	 */
	class Row {
		public int j;

		Row(int j) {
			this.j = j;
		}

		public Cell[] cells = new Cell[TOTAL_COL];

		// 绘制单元格
		public void drawCells(Canvas canvas) {
			for (int i = 0; i < cells.length; i++) {
				if (cells[i] != null) {
					cells[i].drawSelf(canvas);
				}
			}
		}

	}
	int n=0;
	int m=0;
	/**
	 * 单元格元素
	 *
	 * @author wuwenjie
	 *
	 */
	class Cell {
		public CustomDate date;
		public State state;
		public int i;
		public int j;

		public Cell(CustomDate date, State state, int i, int j) {
			super();
			this.date = date;
			this.state = state;
			this.i = i;
			this.j = j;
		}
		public  void  dayDraw(Canvas canvas){



			for( List<String> stateList  :lists){
				for( String   state: stateList) {
					switch (state) {
//						case "normal":        //正常
//							mCirclePaint.setColor(Color.parseColor("#EDF963")); // 红色圆形
//							canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形
//							break;
						case "lateArrive":   //迟到
							mTextPaint.setColor(Color.parseColor("#fffffe"));
							mCirclePaint.setColor(Color.parseColor("#E862F8")); // 红色圆形
							canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形

							break;
						case "earlyLeave":   //早退
							mTextPaint.setColor(Color.parseColor("#fffffe"));
							mCirclePaint.setColor(Color.parseColor("#636DF9")); // 红色圆形
							canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形

							break;
						case "offWork":      //旷工
							mTextPaint.setColor(Color.parseColor("#fffffe"));
							mCirclePaint.setColor(Color.parseColor("#F86262")); // 红色圆形
							canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形

							break;
						case "holiday":      //请假
							mTextPaint.setColor(Color.parseColor("#fffffe"));
							mCirclePaint.setColor(Color.parseColor("#63F970")); // 红色圆形
							canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形

							break;
						case "extraWork":     //加班
							mTextPaint.setColor(Color.parseColor("#fffffe"));
							mCirclePaint.setColor(Color.parseColor("#F39801")); // 红色圆形
							canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形
							break;
						default:
							break;
					}
				}
			}
		}




		public void drawSelf(Canvas canvas) {
//			if(lists!=null &&lists.size()>0){
//				dayDraw(canvas);
//				switch (state){
////					case CURRENT_MONTH_DAY: // 当前月日期
////						dayDraw(canvas);
////					case UNREACH_DAY: // 还未到的天
////						mTextPaint.setColor(Color.BLACK);
////						mCirclePaint2.setColor(Color.parseColor("#ffffff")); // 黄色 EDF963
////						canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint2);// 正方形
////						break;
//				}
//
//
//				// 绘制文字
//				String content = date.day + "";
//				canvas.drawText(content,
//						(float) ((i + 0.5) * mCellSpace - mTextPaint
//								.measureText(content) / 2), (float) ((j + 0.7)
//								* mCellSpace - mTextPaint
//								.measureText(content, 0, 1) / 2), mTextPaint);
//				return;
//			}


			switch (state) {
				case TODAY: // 今天            mCellSpace： 左边小 右边大     上 越小  下越大      半径： mCellSpace / 3 =(mCellSpace/3)
					if(lists!=null&&lists.size()>0 ){
						mTextPaint.setColor(Color.parseColor("#fffffe"));
						mCirclePaint.setColor(Color.parseColor("#1EB6D6"));
						canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形

					}

					break;
				case CURRENT_MONTH_DAY: // 当前月日期
					mTextPaint.setColor(Color.BLACK);

					if(lists!=null&&lists.size()>0 ) {
						for (n=m; n < lists.size(); n++) {
							if(lists.get(n).size()==2){
								mTextPaint.setColor(Color.parseColor("#fffffe"));
								if ("lateArrive".equals(lists.get(n).get(0))) {  //迟到
									mCirclePaint.setColor(Color.parseColor("#E862F8")); //
									canvas.drawRect((int) (mCellSpace * (i + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (i + 0.5)) + ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) + ((mCellSpace/2)-1), mCirclePaint);// 正方形
								}
								if ("lateArrive".equals(lists.get(n).get(1))) {  //迟到
									mCirclePaint.setColor(Color.parseColor("#E862F8")); //
									canvas.drawRect((int) (mCellSpace * (i + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (i + 0.5)) + (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) + (mCellSpace/3), mCirclePaint2);// 正方形
								}


								if ("earlyLeave".equals(lists.get(n).get(1))) {   //早退
									mCirclePaint2.setColor(Color.parseColor("#636DF9")); //
									canvas.drawRect((int) (mCellSpace * (i + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (i + 0.5)) + (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) + (mCellSpace/3), mCirclePaint2);// 正方形

									}

								if ("earlyLeave".equals(lists.get(n).get(0))) {  //早退
									mCirclePaint.setColor(Color.parseColor("#636DF9")); //
									canvas.drawRect((int) (mCellSpace * (i + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (i + 0.5)) + ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) + ((mCellSpace/2)-1), mCirclePaint);// 正方形

								}

								if ("holiday".equals(lists.get(n).get(0))) {   //请假
									mCirclePaint2.setColor(Color.parseColor("#63F970")); //
									//	canvas.drawRect((tX-mCellSpace/3),(tY-mCellSpace/3), (tX+mCellSpace/3), (tY+mCellSpace/3), mCirclePaint2);// 正方形
								//	canvas.drawRect((int) (mCellSpace * (i + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (i + 0.5)) + (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) + (mCellSpace/3), mCirclePaint2);// 正方形
									canvas.drawRect((int) (mCellSpace * (i + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (i + 0.5)) + ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) + ((mCellSpace/2)-1), mCirclePaint);// 正方形

									}
							if ("holiday".equals(lists.get(n).get(1))) {   //请假
								mCirclePaint2.setColor(Color.parseColor("#63F970")); //
							//	canvas.drawRect((int) (mCellSpace * (i + 0.5)) - 42, (int) (mCellSpace * (j + 0.5)) - 42, (int) (mCellSpace * (i + 0.5)) + 42, (int) (mCellSpace * (j + 0.5)) + 42, mCirclePaint2);// 正方形
							//	canvas.drawRect(((int) ((i + 0.5) * mCellSpace )- mCellSpace/3), ((int) ((j + 0.5) * mCellSpace)-mCellSpace/3),  ((int) ((i + 0.5) * mCellSpace )+mCellSpace/3), ((int) ((j + 0.5) * mCellSpace)+mCellSpace/3), mCirclePaint2);// 正方形
								canvas.drawRect((int) (mCellSpace * (i + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (i + 0.5)) + (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) + (mCellSpace/3), mCirclePaint2);// 正方形
							}


								if ("extraWork".equals(lists.get(n).get(0))) {   //加班
									mCirclePaint2.setColor(Color.parseColor("#F39801")); //
									//	canvas.drawRect((tX-mCellSpace/3),(tY-mCellSpace/3), (tX+mCellSpace/3), (tY+mCellSpace/3), mCirclePaint2);// 正方形
								//	canvas.drawRect((int) (mCellSpace * (i + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (i + 0.5)) + (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) + (mCellSpace/3), mCirclePaint2);// 正方形
									canvas.drawRect((int) (mCellSpace * (i + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (i + 0.5)) + ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) + ((mCellSpace/2)-1), mCirclePaint);// 正方形

								}

								if ("extraWork".equals(lists.get(n).get(1))) {   //加班
									mCirclePaint2.setColor(Color.parseColor("#F39801")); //
									//	canvas.drawRect((tX-mCellSpace/3),(tY-mCellSpace/3), (tX+mCellSpace/3), (tY+mCellSpace/3), mCirclePaint2);// 正方形
									canvas.drawRect((int) (mCellSpace * (i + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) - (mCellSpace/3), (int) (mCellSpace * (i + 0.5)) + (mCellSpace/3), (int) (mCellSpace * (j + 0.5)) + (mCellSpace/3), mCirclePaint2);// 正方形

								}

								m++;
								break;
							}


							if("normal".equals(lists.get(n).get(0))){
								mCirclePaint.setColor(Color.parseColor("#EDF963")); // 红色圆形
								canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形
								m++;
								break;
							}
							if ("lateArrive".equals(lists.get(n).get(0))) {  //迟到
								mTextPaint.setColor(Color.parseColor("#fffffe"));
								mCirclePaint.setColor(Color.parseColor("#E862F8")); //
								canvas.drawRect((int) (mCellSpace * (i + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (i + 0.5)) + ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) + ((mCellSpace/2)-1), mCirclePaint);// 正方形
								m++;
								break;
							}
							if ("earlyLeave".equals(lists.get(n).get(0))) {   //早退
								mTextPaint.setColor(Color.parseColor("#fffffe"));
								mCirclePaint.setColor(Color.parseColor("#636DF9")); //
								canvas.drawRect((int) (mCellSpace * (i + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (i + 0.5)) + ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) + ((mCellSpace/2)-1), mCirclePaint);// 正方形
								m++;
								break;
							}
							if ("offWork".equals(lists.get(n).get(0))) {   //旷工
								mTextPaint.setColor(Color.parseColor("#fffffe"));
								mCirclePaint.setColor(Color.parseColor("#F86262"));
								canvas.drawRect((int) (mCellSpace * (i + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (i + 0.5)) + ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) + ((mCellSpace/2)-1), mCirclePaint);// 正方形
								m++;
								break;
							}

							if ("holiday".equals(lists.get(n).get(0))) {   //请假
								mTextPaint.setColor(Color.parseColor("#fffffe"));
								mCirclePaint.setColor(Color.parseColor("#63F970"));
								canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形
								m++;
								break;
							}
							if ("extraWork".equals(lists.get(n).get(0))) {   //请假
								mTextPaint.setColor(Color.parseColor("#fffffe"));
								mCirclePaint.setColor(Color.parseColor("#F39801")); // 红色圆形
								canvas.drawRect((int)(mCellSpace * (i + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))-((mCellSpace/2)-1), (int)(mCellSpace * (i + 0.5))+((mCellSpace/2)-1), (int)(mCellSpace * (j + 0.5))+((mCellSpace/2)-1),mCirclePaint);// 正方形
								m++;
								break;
							}
						}

					}





					break;
				case PAST_MONTH_DAY: // 过去一个月
				case NEXT_MONTH_DAY: // 下一个月
					//	mTextPaint.setColor(Color.parseColor("#F6AA00"));
					mTextPaint.setColor(Color.GRAY);
					break;
				case UNREACH_DAY: // 还未到的天
					//	mTextPaint.setColor(Color.parseColor("#F6AA00"));
					if(lists!=null&&lists.size()>0 ) {
						mTextPaint.setColor(Color.BLACK);
						mCirclePaint.setColor(Color.parseColor("#ffffff"));
						canvas.drawRect((int) (mCellSpace * (i + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) - ((mCellSpace/2)-1), (int) (mCellSpace * (i + 0.5)) + ((mCellSpace/2)-1), (int) (mCellSpace * (j + 0.5)) + ((mCellSpace/2)-1), mCirclePaint);// 正方形
					}

					break;
				default:
					break;
			}
			// 绘制文字
			String content = date.day + "";
			canvas.drawText(content,
					(float) ((i + 0.5) * mCellSpace - mTextPaint
							.measureText(content) / 2), (float) ((j + 0.7)
							* mCellSpace - mTextPaint
							.measureText(content, 0, 1) / 2), mTextPaint);

			Log.e("Fragment","X: "+(float) ((i + 0.5) * mCellSpace - mTextPaint
					.measureText(content) / 2)+" Y: "+(float) ((j + 0.7)
					* mCellSpace - mTextPaint
					.measureText(content, 0, 1) / 2)+"---mCellSpace "+mCellSpace);

		}
	}

	/**
	 *
	 * @author wuwenjie 单元格的状态 当前月日期，过去的月的日期，下个月的日期
	 */
	enum State {
		TODAY,CURRENT_MONTH_DAY, PAST_MONTH_DAY, NEXT_MONTH_DAY, UNREACH_DAY;
	}


	// 当前月
	public void nowSlide(List<List<String>> lists) {
		Logger.i("加载完成"+lists.size());
		this.lists=lists;
		update();
	}


	// 从左往右划，上一个月
	public void leftSlide(List<List<String>> lists) {
		if (mShowDate.month == 1) {
			mShowDate.month = 12;
			mShowDate.year -= 1;
		} else {
			mShowDate.month -= 1;
		}
		this.lists=lists;
		update();
		Logger.i("leftSlide"+lists.size());
	}

	// 从右往左划，下一个月
	public void rightSlide(List<List<String>> lists) {
		if (mShowDate.month == 12) {
			mShowDate.month = 1;
			mShowDate.year += 1;
		} else {
			mShowDate.month += 1;
		}
		this.lists=lists;
		update();
		Logger.i("rightSlide"+lists.size());
	}

	public void update() {
		n=0;
		m=0;
		fillDate();
		invalidate();
	}

}
