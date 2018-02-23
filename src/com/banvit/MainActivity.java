package com.banvit;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.AutoParallaxBackground;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
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

public class MainActivity extends BaseGameActivity {
	BoundCamera _camera;
	Scene _scene;
	private int _WIDTH;
	private int _HEIGHT;
	Texture _Texture, _Texture2, _Texture3, _Toption;
	TextureRegion _Region, _Region2, _Region3, _Roption;

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
		_Texture3 = new Texture(2048, 2048, TextureOptions.DEFAULT);
		_Region3 = TextureRegionFactory.createFromAsset(_Texture3, this,
				"bg6.png", 0, 0);
		mEngine.getTextureManager().loadTexture(_Texture3);
		_Texture = new Texture(512, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region = new TextureRegionFactory().createFromAsset(_Texture, this,
				"newgame.png", 0, 0);
		_Texture2 = new Texture(512, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region2 = new TextureRegionFactory().createFromAsset(_Texture2, this,
				"exit1.png", 0, 0);
		_Toption = new Texture(512, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Roption = new TextureRegionFactory().createFromAsset(_Toption, this,
				"levelmap.png", 0, 0);
		mEngine.getTextureManager().loadTextures(_Texture, _Texture2, _Toption);

	}

	@Override
	public Scene onLoadScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		_scene = new Scene();
		AutoParallaxBackground _Background = new AutoParallaxBackground(0, 0,
				0, 5);
		_Background.attachParallaxEntity(new ParallaxEntity(0, new Sprite(0, 0,
				_WIDTH, _HEIGHT, _Region3)));

		_scene.setBackground(_Background);
		float x = (_WIDTH - _Roption.getWidth()) / 2;
		float y = (_HEIGHT - _Roption.getHeight()) / 2;
		// add sprite play game
		Sprite _option = new Sprite(x, y, _Roption) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				Intent intent = new Intent(MainActivity.this, optionmap.class);
				startActivity(intent);
				return true;
			}
		};
		_scene.attachChild(_option);
		_scene.registerTouchArea(_option);
		float xplay = (_WIDTH - _Region.getWidth())/2;
		Sprite _Sprite = new Sprite(xplay, y - 100, _Region) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				Intent intent = new Intent(MainActivity.this, ScreenGame.class);
				startActivity(intent);				
				return true;
			}

		};
		_scene.attachChild(_Sprite);
		_scene.registerTouchArea(_Sprite);
		// add sprite exit game
		float xexit = (_WIDTH  -_Region2.getWidth())/2;
		Sprite _Sprite2 = new Sprite(xexit, y + 100, _Region2) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				finish();
				return true;
			}

		};
		_scene.attachChild(_Sprite2);
		_scene.registerTouchArea(_Sprite2);

		return _scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

}
