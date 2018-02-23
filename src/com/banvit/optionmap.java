package com.banvit;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;
import android.util.DisplayMetrics;

public class optionmap extends BaseGameActivity {
	BoundCamera _camera;
	Scene _scene;
	private int _WIDTH;
	private int _HEIGHT;
	Texture map1,map2,map3,maplv,back;
	TextureRegion rmap1,rmap2,rmap3,rmaplv,rback;
	@Override
	public Engine onLoadEngine() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		_WIDTH = width;
		_HEIGHT = height;
		_camera = new BoundCamera(0, 0, _WIDTH, _HEIGHT);
		Engine _engine = new Engine(new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(_WIDTH,
						_HEIGHT), _camera).setNeedsMusic(true).setNeedsSound(
				true));
		return _engine;
	}

	@Override
	public void onLoadResources() {
		TextureRegionFactory.setAssetBasePath("gfx/");
		map1 = new Texture(256, 256, TextureOptions.DEFAULT);
		rmap1 = TextureRegionFactory.createFromAsset(map1, this,
				"map1.PNG", 0, 0);
		map2 = new Texture(256, 256, TextureOptions.DEFAULT);
		rmap2 = TextureRegionFactory.createFromAsset(map2, this,
				"map2.PNG", 0, 0);
		map3 = new Texture(256, 256, TextureOptions.DEFAULT);
		rmap3 = TextureRegionFactory.createFromAsset(map3, this,
				"map3.PNG", 0, 0);
		maplv = new Texture(256, 256, TextureOptions.DEFAULT);
		rmaplv = TextureRegionFactory.createFromAsset(maplv, this,
				"maplv.PNG", 0, 0);
		back = new Texture(256, 256, TextureOptions.DEFAULT);
		rback = TextureRegionFactory.createFromAsset(back, this,
				"back.PNG", 0, 0);
		mEngine.getTextureManager().loadTextures(map1, map2,maplv,back,map3);
		
	}

	@Override
	public Scene onLoadScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		_scene = new Scene();
		_scene.setBackground(new ColorBackground(0.09f,0.6f,0.8f));
		float x = (_WIDTH-rmap1.getWidth())/2;
		float y = (_HEIGHT-rmap1.getHeight())/2;
		Sprite spmap1 = new Sprite(x,y-100,rmap1)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				Intent intent = new Intent(optionmap.this, ScreenGame.class);
				startActivity(intent);
				finish();				
				return true;
			}
		};
		_scene.attachChild(spmap1);
		_scene.registerTouchArea(spmap1);
		//
		float xlv = (_WIDTH-rmaplv.getWidth())/2;
		Sprite spmaplv = new Sprite(xlv,y-200,rmaplv);
		spmaplv.setScale(2);
		_scene.attachChild(spmaplv);
		//
		float xmap2 = (_WIDTH-rmap2.getWidth())/2;
		Sprite spmap2 = new Sprite(xmap2,y,rmap2)
		{

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				Intent intent = new Intent(optionmap.this, maplevel2.class);
				startActivity(intent);
				finish();	
				return true;
			}
			
		};
		_scene.attachChild(spmap2);
		_scene.registerTouchArea(spmap2);
		//
		float xmap3 = (_WIDTH-rmap3.getWidth())/2;
		Sprite spmap3 = new Sprite(xmap3,y+100,rmap3)
		{

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				Intent intent = new Intent(optionmap.this, map3.class);
				startActivity(intent);
				finish();	
				return true;
			}
			
		};
		_scene.attachChild(spmap3);
		_scene.registerTouchArea(spmap3);
		//
		float xback = (_WIDTH-rback.getWidth())/2;
		Sprite spback = new Sprite(xback,y+200,rback)
		{

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()){
					finish();
				}
				return super
						.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
			}
			
		};
		spback.setScale(1.5f);
		_scene.attachChild(spback);
		_scene.registerTouchArea(spback);
		return _scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

}
