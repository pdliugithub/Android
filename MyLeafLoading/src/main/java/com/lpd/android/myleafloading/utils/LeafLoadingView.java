package com.lpd.android.myleafloading.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lpd.android.myleafloading.R;

public class LeafLoadingView extends View {

	private static final String TAG = "LeafLoadingView";
	// ����ɫ
	private static final int WHITE_COLOR = 0xfffde399;
	// ��ɫ
	private static final int ORANGE_COLOR = 0xffffa800;
	// �е������С
	private static final int MIDDLE_AMPLITUDE = 13;
	// ��ͬ����֮���������
	private static final int AMPLITUDE_DISPARITY = 5;

	// �ܽ���
	private static final int TOTAL_PROGRESS = 100;
	// Ҷ��Ʈ��һ������������ʱ��
	private static final long LEAF_FLOAT_TIME = 3000;
	// Ҷ����תһ����Ҫ��ʱ��
	private static final long LEAF_ROTATE_TIME = 2000;

	// ���ڿ��ƻ��ƵĽ������������ϣ��µľ���
	private static final int LEFT_MARGIN = 9;
	// ���ڿ��ƻ��ƵĽ����������ҵľ���
	private static final int RIGHT_MARGIN = 25;
	private int mLeftMargin, mRightMargin;
	// �е������С
	private int mMiddleAmplitude = MIDDLE_AMPLITUDE;
	// �����
	private int mAmplitudeDisparity = AMPLITUDE_DISPARITY;

	// Ҷ��Ʈ��һ������������ʱ��
	private long mLeafFloatTime = LEAF_FLOAT_TIME;
	// Ҷ����תһ����Ҫ��ʱ��
	private long mLeafRotateTime = LEAF_ROTATE_TIME;
	private Resources mResources;
	private Bitmap mLeafBitmap;
	private int mLeafWidth, mLeafHeight;

	private Bitmap mOuterBitmap;
	private Rect mOuterSrcRect, mOuterDestRect;
	private int mOuterWidth, mOuterHeight;

	private int mTotalWidth, mTotalHeight;

	private Paint mBitmapPaint, mWhitePaint, mOrangePaint;
	private RectF mWhiteRectF, mOrangeRectF, mArcRectF;
	// ��ǰ����
	private int mProgress;
	// �����ƵĽ��������ֵĿ��
	private int mProgressWidth;
	// ��ǰ���ڵĻ��ƵĽ�������λ��
	private int mCurrentProgressPosition;
	// ���εİ뾶
	private int mArcRadius;

	// arc�����Ͻǵ�x���꣬Ҳ�Ǿ���x�������ʼ��
	private int mArcRightLocation;
	// ���ڲ���Ҷ����Ϣ
	private LeafFactory mLeafFactory;
	// ��������Ҷ����Ϣ
	private List<Leaf> mLeafInfos;
	// ���ڿ���������ӵ�ʱ�䲻����
	private int mAddTime;

	public LeafLoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mResources = getResources();
		mLeftMargin = UiUtils.dipToPx(context, LEFT_MARGIN);
		mRightMargin = UiUtils.dipToPx(context, RIGHT_MARGIN);

		mLeafFloatTime = LEAF_FLOAT_TIME;
		mLeafRotateTime = LEAF_ROTATE_TIME;

		initBitmap();
		initPaint();
		mLeafFactory = new LeafFactory();
		mLeafInfos = mLeafFactory.generateLeafs();

	}

	private void initPaint() {
		mBitmapPaint = new Paint();
		mBitmapPaint.setAntiAlias(true);
		mBitmapPaint.setDither(true);
		mBitmapPaint.setFilterBitmap(true);

		mWhitePaint = new Paint();
		mWhitePaint.setAntiAlias(true);
		mWhitePaint.setColor(WHITE_COLOR);

		mOrangePaint = new Paint();
		mOrangePaint.setAntiAlias(true);
		mOrangePaint.setColor(ORANGE_COLOR);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// ���ƽ�������Ҷ��
		// ֮���԰�Ҷ�ӷ��ڽ���������ƣ���Ҫ�ǲ㼶ԭ��
		drawProgressAndLeafs(canvas);
		// drawLeafs(canvas);

		canvas.drawBitmap(mOuterBitmap, mOuterSrcRect, mOuterDestRect,
				mBitmapPaint);

		postInvalidate();
	}

	private void drawProgressAndLeafs(Canvas canvas) {

		if (mProgress >= TOTAL_PROGRESS) {
			mProgress = 0;
		}
		// mProgressWidthΪ�������Ŀ�ȣ����ݵ�ǰ���������������λ��
		mCurrentProgressPosition = mProgressWidth * mProgress / TOTAL_PROGRESS;
		// ����ǰλ����ͼ����ʾ1��Χ��
		if (mCurrentProgressPosition < mArcRadius) {
			Log.i(TAG, "mProgress = " + mProgress
					+ "---mCurrentProgressPosition = "
					+ mCurrentProgressPosition + "--mArcProgressWidth"
					+ mArcRadius);
			// 1.���ư�ɫARC������orange ARC
			// 2.���ư�ɫ����

			// 1.���ư�ɫARC
			canvas.drawArc(mArcRectF, 90, 180, false, mWhitePaint);

			// 2.���ư�ɫ����
			mWhiteRectF.left = mArcRightLocation;
			canvas.drawRect(mWhiteRectF, mWhitePaint);

			// ����Ҷ��
			drawLeafs(canvas);

			// 3.������ɫ ARC
			// ���߽Ƕ�
			int angle = (int) Math.toDegrees(Math
					.acos((mArcRadius - mCurrentProgressPosition)
							/ (float) mArcRadius));
			// ��ʼ��λ��
			int startAngle = 180 - angle;
			// ɨ���ĽǶ�
			int sweepAngle = 2 * angle;
			Log.i(TAG, "startAngle = " + startAngle);
			canvas.drawArc(mArcRectF, startAngle, sweepAngle, false,
					mOrangePaint);
		} else {
			Log.i(TAG, "mProgress = " + mProgress
					+ "---transfer-----mCurrentProgressPosition = "
					+ mCurrentProgressPosition + "--mArcProgressWidth"
					+ mArcRadius);
			// 1.����white RECT
			// 2.����Orange ARC
			// 3.����orange RECT
			// ����㼶���л�������Ҷ�Ӹо���������ɫ��������

			// 1.����white RECT
			mWhiteRectF.left = mCurrentProgressPosition;
			canvas.drawRect(mWhiteRectF, mWhitePaint);
			// ����Ҷ��
			drawLeafs(canvas);
			// 2.����Orange ARC
			canvas.drawArc(mArcRectF, 90, 180, false, mOrangePaint);
			// 3.����orange RECT
			mOrangeRectF.left = mArcRightLocation;
			mOrangeRectF.right = mCurrentProgressPosition;
			canvas.drawRect(mOrangeRectF, mOrangePaint);

		}

	}

	/**
	 * ����Ҷ��
	 * 
	 * @param canvas
	 */
	private void drawLeafs(Canvas canvas) {
		mLeafRotateTime = mLeafRotateTime <= 0 ? LEAF_ROTATE_TIME
				: mLeafRotateTime;
		long currentTime = System.currentTimeMillis();
		for (int i = 0; i < mLeafInfos.size(); i++) {
			Leaf leaf = mLeafInfos.get(i);
			if (currentTime > leaf.startTime && leaf.startTime != 0) {
				// ����Ҷ�ӣ�������Ҷ�ӵ����ͺ͵�ǰʱ��ó�Ҷ�ӵģ�x��y��
				getLeafLocation(leaf, currentTime);
				// ����ʱ�������ת�Ƕ�
				canvas.save();
				// ͨ��Matrix����Ҷ����ת
				Matrix matrix = new Matrix();
				float transX = mLeftMargin + leaf.x;
				float transY = mLeftMargin + leaf.y;
				Log.i(TAG, "left.x = " + leaf.x + "--leaf.y=" + leaf.y);
				matrix.postTranslate(transX, transY);
				// ͨ��ʱ�������ת�Ƕȣ������ֱ��ͨ���޸�LEAF_ROTATE_TIME����Ҷ����ת����
				float rotateFraction = ((currentTime - leaf.startTime) % mLeafRotateTime)
						/ (float) mLeafRotateTime;
				int angle = (int) (rotateFraction * 360);
				// ����Ҷ����ת����ȷ��Ҷ����ת�Ƕ�
				int rotate = leaf.rotateDirection == 0 ? angle
						+ leaf.rotateAngle : -angle + leaf.rotateAngle;
				matrix.postRotate(rotate, transX + mLeafWidth / 2, transY
						+ mLeafHeight / 2);
				canvas.drawBitmap(mLeafBitmap, matrix, mBitmapPaint);
				canvas.restore();
			} else {
				continue;
			}
		}
	}

	private void getLeafLocation(Leaf leaf, long currentTime) {
		long intervalTime = currentTime - leaf.startTime;
		mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
		if (intervalTime < 0) {
			return;
		} else if (intervalTime > mLeafFloatTime) {
			leaf.startTime = System.currentTimeMillis()
					+ new Random().nextInt((int) mLeafFloatTime);
		}

		float fraction = (float) intervalTime / mLeafFloatTime;
		leaf.x = (int) (mProgressWidth - mProgressWidth * fraction);
		leaf.y = getLocationY(leaf);
	}

	// ͨ��Ҷ����Ϣ��ȡ��ǰҶ�ӵ�Yֵ
	private int getLocationY(Leaf leaf) {
		// y = A(wx+Q)+h
		float w = (float) ((float) 2 * Math.PI / mProgressWidth);
		float a = mMiddleAmplitude;
		switch (leaf.type) {
		case LITTLE:
			// С��� �� �е���� �� �����
			a = mMiddleAmplitude - mAmplitudeDisparity;
			break;
		case MIDDLE:
			a = mMiddleAmplitude;
			break;
		case BIG:
			// С��� �� �е���� + �����
			a = mMiddleAmplitude + mAmplitudeDisparity;
			break;
		default:
			break;
		}
		Log.i(TAG, "---a = " + a + "---w = " + w + "--leaf.x = " + leaf.x);
		return (int) (a * Math.sin(w * leaf.x)) + mArcRadius * 2 / 3;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private void initBitmap() {
		mLeafBitmap = ((BitmapDrawable) mResources
				.getDrawable(R.drawable.pic_leaf)).getBitmap();
		mLeafWidth = mLeafBitmap.getWidth();
		mLeafHeight = mLeafBitmap.getHeight();

		mOuterBitmap = ((BitmapDrawable) mResources
				.getDrawable(R.drawable.pic_leaf_bg)).getBitmap();
		mOuterWidth = mOuterBitmap.getWidth();
		mOuterHeight = mOuterBitmap.getHeight();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mTotalWidth = w;
		mTotalHeight = h;
		mProgressWidth = mTotalWidth - mLeftMargin - mRightMargin;
		mArcRadius = (mTotalHeight - 2 * mLeftMargin) / 2;

		mOuterSrcRect = new Rect(0, 0, mOuterWidth, mOuterHeight);
		mOuterDestRect = new Rect(0, 0, mTotalWidth, mTotalHeight);

		mWhiteRectF = new RectF(mLeftMargin + mCurrentProgressPosition,
				mLeftMargin, mTotalWidth - mRightMargin, mTotalHeight
						- mLeftMargin);
		mOrangeRectF = new RectF(mLeftMargin + mArcRadius, mLeftMargin,
				mCurrentProgressPosition, mTotalHeight - mLeftMargin);

		mArcRectF = new RectF(mLeftMargin, mLeftMargin, mLeftMargin + 2
				* mArcRadius, mTotalHeight - mLeftMargin);
		mArcRightLocation = mLeftMargin + mArcRadius;
	}

	private enum StartType {
		LITTLE, MIDDLE, BIG
	}

	/**
	 * Ҷ�Ӷ���������¼Ҷ����Ҫ����
	 * 
	 * @author Ajian_Studio
	 */
	private class Leaf {

		// �ڻ��Ʋ��ֵ�λ��
		float x, y;
		// ����Ҷ��Ʈ���ķ���
		StartType type;
		// ��ת�Ƕ�
		int rotateAngle;
		// ��ת����--0����˳ʱ�룬1������ʱ��
		int rotateDirection;
		// ��ʼʱ��(ms)
		long startTime;
	}

	private class LeafFactory {
		private static final int MAX_LEAFS = 8;
		Random random = new Random();

		// ����һ��Ҷ����Ϣ
		public Leaf generateLeaf() {
			Leaf leaf = new Leaf();
			int randomType = random.nextInt(3);
			// ��ʱ���ͣ� ������
			StartType type = StartType.MIDDLE;
			switch (randomType) {
			case 0:
				break;
			case 1:
				type = StartType.LITTLE;
				break;
			case 2:
				type = StartType.BIG;
				break;
			default:
				break;
			}
			leaf.type = type;
			// �����ʼ����ת�Ƕ�
			leaf.rotateAngle = random.nextInt(360);
			// �����ת����˳ʱ�����ʱ�룩
			leaf.rotateDirection = random.nextInt(2);
			// Ϊ�˲�������ĸо����ÿ�ʼ��ʱ����һ���������
			mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME
					: mLeafFloatTime;
			mAddTime += random.nextInt((int) (mLeafFloatTime * 2));
			leaf.startTime = System.currentTimeMillis() + mAddTime;
			return leaf;
		}

		// �������Ҷ��������Ҷ����Ϣ
		public List<Leaf> generateLeafs() {
			return generateLeafs(MAX_LEAFS);
		}

		// ���ݴ����Ҷ����������Ҷ����Ϣ
		public List<Leaf> generateLeafs(int leafSize) {
			List<Leaf> leafs = new LinkedList<Leaf>();
			for (int i = 0; i < leafSize; i++) {
				leafs.add(generateLeaf());
			}
			return leafs;
		}
	}

	/**
	 * �����е����
	 * 
	 * @param amplitude
	 */
	public void setMiddleAmplitude(int amplitude) {
		this.mMiddleAmplitude = amplitude;
	}

	/**
	 * ���������
	 * 
	 * @param disparity
	 */
	public void setMplitudeDisparity(int disparity) {
		this.mAmplitudeDisparity = disparity;
	}

	/**
	 * ��ȡ�е����
	 * 
	 * @param amplitude
	 */
	public int getMiddleAmplitude() {
		return mMiddleAmplitude;
	}

	/**
	 * ��ȡ�����
	 * 
	 * @param disparity
	 */
	public int getMplitudeDisparity() {
		return mAmplitudeDisparity;
	}

	/**
	 * ���ý���
	 * 
	 * @param progress
	 */
	public void setProgress(int progress) {
		this.mProgress = progress;
		postInvalidate();
	}

	/**
	 * ����Ҷ��Ʈ��һ������������ʱ��
	 * 
	 * @param time
	 */
	public void setLeafFloatTime(long time) {
		this.mLeafFloatTime = time;
	}

	/**
	 * ����Ҷ����תһ��������ʱ��
	 * 
	 * @param time
	 */
	public void setLeafRotateTime(long time) {
		this.mLeafRotateTime = time;
	}

	/**
	 * ��ȡҶ��Ʈ��һ������������ʱ��
	 */
	public long getLeafFloatTime() {
		mLeafFloatTime = mLeafFloatTime == 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
		return mLeafFloatTime;
	}

	/**
	 * ��ȡҶ����תһ��������ʱ��
	 */
	public long getLeafRotateTime() {
		mLeafRotateTime = mLeafRotateTime == 0 ? LEAF_ROTATE_TIME
				: mLeafRotateTime;
		return mLeafRotateTime;
	}
}
