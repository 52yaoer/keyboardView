package com.apicloud.keyboardview;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.apicloud.keyboardview.SoftKeyBoardListener.OnSoftKeyBoardChangeListener;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZResourcesIDFinder;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzkit.UZUtility;

public class KeyboardViewModule extends UZModule implements OnSoftKeyBoardChangeListener{
	
	private View keyBoardView;
	private int keyBoardHeight ;
	
	private int mHeight = 40;
	private String mBackgroundColor;
	private String mLineColor;
	private List<DataBean> dataList = new ArrayList<DataBean>();
	
	private UZModuleContext keyboardViewContext;
	private Activity mActivity;
	public KeyboardViewModule(UZWebView webView) {
		super(webView);
	}
	
	public void jsmethod_addKeyboardListener(final UZModuleContext moduleContext){
		keyboardViewContext = moduleContext;
		mActivity = getContext();
		mHeight = moduleContext.optInt("height",40);
		mBackgroundColor = moduleContext.optString("backgroundColor");
		mLineColor = moduleContext.optString("lineColor");
		JSONArray data = moduleContext.optJSONArray("data");
		if(data==null){
			errorPulbic(moduleContext, "data is null");
			return;
		}
		
		if(keyBoardView!=null){
			keyBoardView.setVisibility(View.INVISIBLE);
			removeViewFromCurWindow(keyBoardView);
			keyBoardView = null;
		}
		
		if (dataList.size() > 0) {
            dataList.clear();
        }
		
		//初始化数据
        for(int i = 0; i <data.length() ; i++){
        	JSONObject obj = data.optJSONObject(i);
        	String id = obj.optString("id");
        	String icon = obj.optString("icon");
        	
        	Bitmap bitMap = null;
    		try {
    			if (TextUtils.isEmpty(icon)) {
    				ApplicationInfo info = mActivity.getPackageManager().getApplicationInfo(mActivity.getPackageName(), 0);
    				Drawable iconTemp = info.loadIcon(mActivity.getPackageManager());
    				bitMap = drawableToBitmap(iconTemp);
    			}else{
    				icon = makeRealPath(icon);
    				bitMap = UZUtility.getLocalImage(icon);
    				if(bitMap==null){
    					ApplicationInfo info = mActivity.getPackageManager().getApplicationInfo(mActivity.getPackageName(), 0);
    					Drawable iconTemp = info.loadIcon(mActivity.getPackageManager());
    					bitMap = drawableToBitmap(iconTemp);
    				}
    			}
    		} catch (Exception e1) {
    			try {
    				ApplicationInfo info = mActivity.getPackageManager().getApplicationInfo(mActivity.getPackageName(), 0);
    				Drawable iconTemp = info.loadIcon(mActivity.getPackageManager());
    				bitMap = drawableToBitmap(iconTemp);
    			} catch (Exception e) {
    			}
    		}
    		
    		if(bitMap==null){
    			try {
    				ApplicationInfo info = mActivity.getPackageManager().getApplicationInfo(mActivity.getPackageName(), 0);
    				Drawable iconTemp = info.loadIcon(mActivity.getPackageManager());
    				bitMap = drawableToBitmap(iconTemp);
    			} catch (Exception e) {
    			}
    		}
        	
        	DataBean bean = new DataBean();
        	bean.index = i;
        	bean.id = id;
        	bean.bitMap = bitMap;
        	
        	dataList.add(bean);
        }
        SoftKeyBoardListener.setListener(mActivity, this);
		successPublic(keyboardViewContext, "init");
	}
	
	@Override
    public void keyBoardShow(int rootHeight,int height) {
    	if(keyboardViewContext==null){
    		return;
    	}
    	showKeyBoardBar(rootHeight,height);
    }

    @Override
    public void keyBoardHide(int rootHeight,int height) {
    	if(keyboardViewContext==null){
    		return;
    	}
    	hideKeyBoardBar();
    }
	
	public void showKeyBoardBar(final int rootHeight,final int height){
		if(keyBoardView==null){
			LayoutInflater inflater = LayoutInflater.from(mActivity);
			int layid = UZResourcesIDFinder.getResLayoutID("keyboard_main_gridview");
			keyBoardView = inflater.inflate(layid, null);
			if(TextUtils.isEmpty(mBackgroundColor) || !mBackgroundColor.startsWith("#")){
				keyBoardView.setBackgroundColor(Color.WHITE);
			}else{
				keyBoardView.setBackgroundColor(UZCoreUtil.parseColor(mBackgroundColor));
			}
			keyBoardView.setVisibility(View.INVISIBLE);
			if(TextUtils.isEmpty(mLineColor) || !mLineColor.startsWith("#")){
				
			}else{
				View line = keyBoardView.findViewById(UZResourcesIDFinder.getResIdID("line"));
				line.setBackgroundColor(UZCoreUtil.parseColor(mLineColor));
			}
			
			GridView gridView = (GridView)keyBoardView.findViewById(UZResourcesIDFinder.getResIdID("gridView"));
			GridViewAdapter adapter = new GridViewAdapter(dataList, UZUtility.dipToPix(mHeight-1));
			gridView.setNumColumns(dataList.size());
			gridView.setAdapter(adapter);
			gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					DataBean bean = dataList.get(view.getId());
					if(TextUtils.isEmpty(bean.id)){
						successPublic(keyboardViewContext, "click", "id", bean.index);
					}else{
						successPublic(keyboardViewContext, "click", "id", bean.id);
					}
				}
			});
			
			
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, mHeight);
			params.leftMargin = 0;
			params.topMargin = 0;
			insertViewToCurWindow(keyBoardView, params);
			
			keyBoardView.getViewTreeObserver().addOnPreDrawListener(
					new OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					keyBoardView.getViewTreeObserver().removeOnPreDrawListener(this);
					keyBoardHeight = keyBoardView.getHeight();
					int y = rootHeight - height - keyBoardHeight;
					if(inImmerseState()){
						int result = 0;
						int resourceId = mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
						if (resourceId > 0) {
						    result = mActivity.getResources().getDimensionPixelSize(resourceId);
						}
						y  += result;
					}
					removeViewFromCurWindow(keyBoardView);
					RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, mHeight);
					params1.leftMargin = 0;
					params1.topMargin = UZCoreUtil.pixToDip(y);
					insertViewToCurWindow(keyBoardView, params1);
					runOnUiThreadDelay(new Runnable() {
						@Override
						public void run() {
							try {
								keyBoardView.setVisibility(View.VISIBLE);
								successPublic(keyboardViewContext, "show");
							} catch (Exception e) {
							}
						}
					}, 200);
					return true;
				}
			});
		}else{
			removeViewFromCurWindow(keyBoardView);
			int y = rootHeight - height - keyBoardHeight;
			if(inImmerseState()){
				int result = 0;
				int resourceId = mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
				if (resourceId > 0) {
				    result = mActivity.getResources().getDimensionPixelSize(resourceId);
				}
				y  += result;
			}
			RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, mHeight);
			params1.leftMargin = 0;
			params1.topMargin = UZCoreUtil.pixToDip(y);
			insertViewToCurWindow(keyBoardView, params1);
			runOnUiThreadDelay(new Runnable() {
				@Override
				public void run() {
					try {
						keyBoardView.setVisibility(View.VISIBLE);
						successPublic(keyboardViewContext, "show");
					} catch (Exception e) {
					}
				}
			}, 200);
		}
	}
	
	private void hideKeyBoardBar(){
		if(keyBoardView!=null){
			keyBoardView.setVisibility(View.INVISIBLE);
			removeViewFromCurWindow(keyBoardView);
			successPublic(keyboardViewContext, "hide");
		}
	}
	
	public void jsmethod_removeKeyboardListener(final UZModuleContext moduleContext){
		if(keyBoardView==null && keyboardViewContext==null){
			errorPulbic(moduleContext, "addKeyboardListener not init");
			return;
		}
		if(keyBoardView!=null){
			keyBoardView.setVisibility(View.INVISIBLE);
			removeViewFromCurWindow(keyBoardView);
			keyBoardView = null;
		}
		keyboardViewContext = null;
		successPublic(moduleContext);
	}
	

	@Override
	protected void onClean() {
		hideKeyBoardBar();
		keyBoardView = null;
		keyboardViewContext = null;
	}
	
	public void successPublic(final UZModuleContext moduleContext){
		successPublic(moduleContext, true);
	}
	
	public void successPublic(final UZModuleContext moduleContext,final boolean status){
		JSONObject ret = new JSONObject();
		try {
			ret.put("status", status);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if(moduleContext!=null){
			moduleContext.success(ret, false);
		}
	}
	
	public void successPublic(UZModuleContext moduleContext,String key,Object value){
		JSONObject ret = new JSONObject();
		try {
			ret.put("status", true);
			if(!TextUtils.isEmpty(key)){
				ret.put(key, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if(moduleContext!=null){
			moduleContext.success(ret, false);
		}
	}
	
	public void successPublic(UZModuleContext moduleContext,String evenType){
		successPublic(moduleContext, evenType, null, null);
	}
	
	public void successPublic(UZModuleContext moduleContext,String evenType,String key,Object value){
		JSONObject ret = new JSONObject();
		try {
			ret.put("status", true);
			ret.put("evenType", evenType);
			if(!TextUtils.isEmpty(key)){
				ret.put(key, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if(moduleContext!=null){
			moduleContext.success(ret, false);
		}
	}
	
	
	public void errorPulbic(final UZModuleContext moduleContext,final String message){
		JSONObject ret = new JSONObject();
		JSONObject err = new JSONObject();
		try {
			ret.put("status", false);
			err.put("msg", message);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(moduleContext!=null){
			moduleContext.error(ret, err, false);
		}
	}
	
	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(
		drawable.getIntrinsicWidth(),
		drawable.getIntrinsicHeight(),
		drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}
}
