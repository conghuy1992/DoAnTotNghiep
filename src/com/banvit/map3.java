package com.banvit;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl.IOnScreenControlListener;
import org.anddev.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.AlphaModifier;
import org.anddev.andengine.entity.modifier.DelayModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.MoveXModifier;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.modifier.ParallelEntityModifier;
import org.anddev.andengine.entity.modifier.RotationModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.AutoParallaxBackground;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.modifier.IModifier;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class map3 extends BaseGameActivity implements IOnSceneTouchListener {
	String prefname = "my_data3";
	BoundCamera _camera;
	Scene _scene, _Scene2, _Scene3;
	private int _WIDTH;
	private int _HEIGHT;
	Texture _Texture, _Texture2, _Texture3, _Texture4, _Texture5, _Texture6,
			_Texture10, _Texture9, _Texture7, _Texture8, _Texture11,
			_Texture12, bonus, bonus3, target6, bird, _texture14, _texture13,_texture15;
	TextureRegion _Region, _Region3, _Region5, _Region8, _Region9,
			_Region11, _Region10, _Region12, Rbonus, Rbonus3, Rtarget6,_region14,_region13,_region15;
	TiledTextureRegion _TiledTextureRegion, _Region6, _Region4, Rbird;

	LinkedList targetLL6, addTarget6, targetLL, addTarget, dan, addDan, addNo,
			addBonus, addBonus3, targetLL3, addTarget3, target3, addtarget3,
			targetll5, addtarget5, targetll4, addtarget4,addroi, addrunglong,roiLL,runglongLL;
	AnimatedSprite target, player, target4;

	Sound _shoot, _shoot2, _sound5;
	Texture texturetextchange, texturetextchange2, texturetextchange3;
	Font fonttextchange, fonttextchange2, fonttextchange3;
	long _diem = 0, _loi = 0, deadline = 500;
	ChangeableText _textscore, _textHighScore, _texttime;
	Texture _TextureControl;
	TextureRegion _control1, _control2;
	Music _music;
	int _stt, diembonus, diembonus3;

	Texture _volume, _playpause, _restart, _loser, ngaivat;
	TiledTextureRegion _Rvolume, _Rplaypause;
	TextureRegion _Rrestart, _Rloser, Rngaivat;
	Sprite _rsGame, _loserGame, _winGame;
	AnimatedSprite playpause, _mute;
	DigitalOnScreenControl _control;
	Sprite resume;
	boolean runningFlag = true;
	boolean pauseFlag = false;

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
		//
		ngaivat = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Rngaivat = new TextureRegionFactory().createFromAsset(ngaivat, this,
				"stone.PNG", 0, 0);
		mEngine.getTextureManager().loadTexture(ngaivat);
		//
		target6 = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Rtarget6 = new TextureRegionFactory().createFromAsset(target6, this,
				"target6.png", 0, 0);
		mEngine.getTextureManager().loadTexture(target6);
		//
		_Texture = new Texture(2048, 2048, TextureOptions.DEFAULT);
		_Region = TextureRegionFactory.createFromAsset(_Texture, this,
				"bg5.png", 0, 0);
		mEngine.getTextureManager().loadTexture(_Texture);
		//
		bonus = new Texture(256, 256, TextureOptions.DEFAULT);
		Rbonus = TextureRegionFactory.createFromAsset(bonus, this, "bonus.PNG",
				0, 0);
		mEngine.getTextureManager().loadTexture(bonus);
		//
		bonus3 = new Texture(256, 256, TextureOptions.DEFAULT);
		Rbonus3 = TextureRegionFactory.createFromAsset(bonus3, this,
				"bonus3.PNG", 0, 0);
		mEngine.getTextureManager().loadTexture(bonus3);
		//
		_Texture2 = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_TiledTextureRegion = TextureRegionFactory.createTiledFromAsset(
				_Texture2, this, "VitBay.png", 0, 0, 3, 1);
		mEngine.getTextureManager().loadTexture(_Texture2);
		//
		_Texture3 = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region3 = TextureRegionFactory.createFromAsset(_Texture3, this,
				"vukhi.PNG", 0, 0);
		mEngine.getTextureManager().loadTexture(_Texture3);
		//
		//
		_Texture4 = new Texture(1024, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region4 = TextureRegionFactory.createTiledFromAsset(_Texture4, this,
				"hunter1.png", 0, 0, 10, 2);
		mEngine.getTextureManager().loadTexture(_Texture4);
		//
		_Texture5 = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region5 = TextureRegionFactory.createFromAsset(_Texture5, this,
				"cay3.png", 0, 0);
		mEngine.getTextureManager().loadTexture(_Texture5);
		//
		_Texture6 = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region6 = TextureRegionFactory.createTiledFromAsset(_Texture6, this,
				"lua.PNG", 0, 0, 3, 1);
		mEngine.getTextureManager().loadTexture(_Texture6);
		//
		
		//
		_Texture10 = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region10 = TextureRegionFactory.createFromAsset(_Texture10, this,
				"playgame.PNG", 0, 0);
		mEngine.getTextureManager().loadTexture(_Texture10);
		//
		SoundFactory.setAssetBasePath("mfx/");
		try {
			_shoot = SoundFactory.createSoundFromAsset(
					mEngine.getSoundManager(), this, "sound.mp3");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		try {
			_shoot2 = SoundFactory.createSoundFromAsset(
					mEngine.getSoundManager(), this, "explosion.ogg");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			_sound5 = SoundFactory.createSoundFromAsset(
					mEngine.getSoundManager(), this, "sound5.mp3");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		MusicFactory.setAssetBasePath("mfx/");
		try {
			_music = MusicFactory.createMusicFromAsset(
					mEngine.getMusicManager(), this, "bg.mp3");
			_music.setLooping(true);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		texturetextchange = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		fonttextchange = new Font(texturetextchange, Typeface.create(
				Typeface.MONOSPACE, Typeface.BOLD), 30, true, Color.RED);
		mEngine.getTextureManager().loadTexture(texturetextchange);
		mEngine.getFontManager().loadFont(fonttextchange);
		//
		texturetextchange2 = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		fonttextchange2 = new Font(texturetextchange2, Typeface.create(
				Typeface.MONOSPACE, Typeface.BOLD), 40, true, Color.GREEN);
		mEngine.getTextureManager().loadTexture(texturetextchange2);
		mEngine.getFontManager().loadFont(fonttextchange2);
		//
		texturetextchange3 = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		fonttextchange3 = new Font(texturetextchange3, Typeface.create(
				Typeface.MONOSPACE, Typeface.BOLD), 40, true, Color.BLUE);
		mEngine.getTextureManager().loadTexture(texturetextchange3);
		mEngine.getFontManager().loadFont(fonttextchange3);
		//
		_TextureControl = new Texture(256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_control1 = TextureRegionFactory.createFromAsset(_TextureControl, this,
				"onscreen_control_base.png", 0, 0);
		_control2 = TextureRegionFactory.createFromAsset(_TextureControl, this,
				"onscreen_control_knob.png", 128, 0);
		mEngine.getTextureManager().loadTexture(_TextureControl);
		//
		_Texture8 = new Texture(2048, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region8 = TextureRegionFactory.createFromAsset(_Texture8, this,
				"mattroi.png", 0, 0);

		mEngine.getTextureManager().loadTexture(_Texture8);
		//
		_Texture9 = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region9 = TextureRegionFactory.createFromAsset(_Texture9, this,
				"no2.PNG", 0, 0);
		mEngine.getTextureManager().loadTexture(_Texture9);

		//
		_Texture11 = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region11 = TextureRegionFactory.createFromAsset(_Texture11, this,
				"win.PNG", 0, 0);
		mEngine.getTextureManager().loadTexture(_Texture11);
		//
		_Texture12 = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Region12 = TextureRegionFactory.createFromAsset(_Texture12, this,
				"loser.PNG", 0, 0);
		mEngine.getTextureManager().loadTexture(_Texture12);
		//
		_volume = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Rvolume = new TextureRegionFactory().createTiledFromAsset(_volume,
				this, "volume.PNG", 0, 0, 2, 1);
		mEngine.getTextureManager().loadTexture(_volume);
		//
		//

		//
		_playpause = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Rplaypause = new TextureRegionFactory().createTiledFromAsset(
				_playpause, this, "playpause.PNG", 0, 0, 2, 1);
		mEngine.getTextureManager().loadTexture(_playpause);
		//
		_restart = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Rrestart = new TextureRegionFactory().createFromAsset(_restart, this,
				"restart.PNG", 0, 0);
		mEngine.getTextureManager().loadTexture(_restart);
		//
		_loser = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_Rloser = new TextureRegionFactory().createFromAsset(_loser, this,
				"loser.PNG", 0, 0);
		mEngine.getTextureManager().loadTexture(_loser);
		//
		bird = new Texture(1024, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Rbird = TextureRegionFactory.createTiledFromAsset(bird, this,
				"capro.png", 0, 0, 3, 1);
		mEngine.getTextureManager().loadTexture(bird);
		//
		_texture14 = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_region14 = TextureRegionFactory.createFromAsset(_texture14, this, "long.png",0,0);
		mEngine.getTextureManager().loadTexture(_texture14);
		
		_texture13 = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_region13 = TextureRegionFactory.createFromAsset(_texture13, this, "VitRoi.png",0,0);
		mEngine.getTextureManager().loadTexture(_texture13);
		_texture15 = new Texture(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		_region15 = TextureRegionFactory.createFromAsset(_texture15, this, "caroi.png",0,0);
		mEngine.getTextureManager().loadTexture(_texture15);
	}

	@Override
	public Scene onLoadScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		_scene = new Scene();
		//
		AutoParallaxBackground _Background = new AutoParallaxBackground(0, 0,
				0, 5);
		_Background.attachParallaxEntity(new ParallaxEntity(-2, new Sprite(0,
				0, _WIDTH, _HEIGHT, _Region)));
		_Background.attachParallaxEntity(new ParallaxEntity(-7, new Sprite(0,
				_HEIGHT - _Region5.getHeight(), _Region5)));
		float xtrang = (_WIDTH - _Region8.getWidth()) / 2;
		_Background.attachParallaxEntity(new ParallaxEntity(-5, new Sprite(
				xtrang, 30, _Region8)));
		_scene.setBackground(_Background);
		//

		//
		texturetextchange = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		fonttextchange = new Font(texturetextchange, Typeface.create(
				Typeface.MONOSPACE, Typeface.BOLD), 40, true, Color.RED);
		mEngine.getTextureManager().loadTexture(texturetextchange);
		mEngine.getFontManager().loadFont(fonttextchange);
		_textscore = new ChangeableText(20, 20, fonttextchange, "  SCORE:0");
		_scene.attachChild(_textscore);
		//
		texturetextchange2 = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		fonttextchange2 = new Font(texturetextchange2, Typeface.create(
				Typeface.MONOSPACE, Typeface.BOLD), 40, true, Color.GREEN);
		mEngine.getTextureManager().loadTexture(texturetextchange2);
		mEngine.getFontManager().loadFont(fonttextchange2);
		_textHighScore = new ChangeableText(250, 20, fonttextchange2,
				"   BEST:");
		_scene.attachChild(_textHighScore);
		//
		texturetextchange3 = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		fonttextchange3 = new Font(texturetextchange3, Typeface.create(
				Typeface.MONOSPACE, Typeface.BOLD), 40, true, Color.BLUE);
		mEngine.getTextureManager().loadTexture(texturetextchange3);
		mEngine.getFontManager().loadFont(fonttextchange3);
		_texttime = new ChangeableText(480, 20, fonttextchange3, "  TIME: ");
		_scene.attachChild(_texttime);
		CountDownTimer timer = new CountDownTimer(99999999, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				deadline--;
				_texttime.setText("TIME:" + deadline);
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub

			}
		};
		timer.start();
		//
		float x = (_WIDTH - _Region4.getWidth()) / 2;
		float y = _HEIGHT - _Region4.getHeight() + 60;
		player = new AnimatedSprite(x, y, _Region4);
		player.animate(new long[] { 70, 70, 70, 70, 70, 70, 70, 70, 70,70}, 0, 9, true);
		player.setScale(1.4f);
		// đăng ký cho sprite chuyển động vật lý, khi đi�?u khiển control thì mới
		// di chuyển
		final PhysicsHandler _handler = new PhysicsHandler(player);
		player.registerUpdateHandler(_handler);// đăng ký sprite 1 chuyển động
												// _handler
		_scene.attachChild(player);
		//

		//
		float xcontrol = ((_control1.getWidth()) / 3) - 30;
		_control = new DigitalOnScreenControl(xcontrol, _HEIGHT
				- _control1.getHeight(), _camera, _control1, _control2, 0.3f,
				new IOnScreenControlListener() {

					@Override
					public void onControlChange(
							BaseOnScreenControl pBaseOnScreenControl,
							float pValueX, float pValueY) {
						// TODO Auto-generated method stub

						if (pValueX != 0 || pValueY != 0) {
							// sang phai
							if (pValueX > 0) {
								player.animate(new long[] { 70, 70, 70, 70, 70, 70, 70, 70, 70,70}, 0,
										9, true);
								_stt = 1;
								_handler.setVelocity(pValueX * 150,
										pValueY * 150);
								if (player.getX() >= _WIDTH - 100) {
									_handler.setVelocity(pValueX * 0,
											pValueY * 0);
								}
							}
							// sang trai
							if (pValueX < 0) {
								player.animate(new long[] { 70, 70, 70, 70, 70, 70, 70, 70, 70,70}, 10,
										19, true);
								_stt = 2;
								_handler.setVelocity(pValueX * 150,
										pValueY * 150);
								if (player.getX() <= 100) {
									_handler.setVelocity(pValueX * 0,
											pValueY * 0);
								}
							}
							if (pValueY > 0) {
								_handler.setVelocity(pValueX * 0, pValueY * 0);

							}
							if (pValueY < 0) {
								_handler.setVelocity(pValueX * 0, pValueY * 0);

							}

						} else {
							if (_stt == 1) {
								// nếu statuscar =1 : đang sang phải mà đứng lại
								// thì gán cho nó 1 trong số hình số 6,7,8

								player.animate(new long[] { 200 },
										new int[] { 0 }, 10000);
							}
							if (_stt == 2) {
								player.animate(new long[] { 200 },
										new int[] { 19 }, 10000);
							}
							_stt = 0;
							_handler.setVelocity(pValueX * 0, pValueY * 0);
						}
					}
				});
		_control.getControlBase().setBlendFunction(GL10.GL_SRC_ALPHA,
				GL10.GL_ONE_MINUS_SRC_ALPHA);
		_control.getControlBase().setScaleCenter(0, 128);
		_control.getControlBase().setAlpha(0.6f);
		_control.getControlBase().setScale(1.5f);
		_control.refreshControlKnobPosition();
		_scene.setChildScene(_control);
		//
		targetLL = new LinkedList();
		addTarget = new LinkedList();
		dan = new LinkedList();
		addDan = new LinkedList();
		addNo = new LinkedList();
		addBonus = new LinkedList();
		addBonus3 = new LinkedList();
		targetLL3 = new LinkedList();
		addTarget3 = new LinkedList();
		targetll5 = new LinkedList();
		addtarget5 = new LinkedList();
		targetLL6 = new LinkedList();
		addTarget6 = new LinkedList();
		targetll4 = new LinkedList();
		addtarget4 = new LinkedList();
		addroi = new LinkedList();
		addrunglong = new LinkedList();
		roiLL = new LinkedList();
		runglongLL = new LinkedList();
		//

		//
		_addtarget();
		createSpriteSpawnTimeHandler();
		_scene.registerUpdateHandler(detect);
		
		_addtarget5();
		createSpriteTarget5();
		_addtarget6();
		createSpriteTarget6();
		_addtarget4();
		createSpriteTarget4();
		//
		_scene.setOnSceneTouchListener(this);
		//

		//
		_music.play();
		//
		float xmute = _WIDTH - _Rvolume.getWidth();
		float ymute = 20;
		_mute = new AnimatedSprite(xmute, ymute, _Rvolume) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if (_music.isPlaying()) {
						_music.pause();
						_shoot.setVolume(0);
						_shoot2.setVolume(0);
						this.setCurrentTileIndex(1, 0);
					} else {
						_music.play();
						_shoot.setVolume(80);
						_shoot2.setVolume(80);
						this.setCurrentTileIndex(0, 0);
					}
					return true;
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};
		_mute.setScale(1.5f);
		_scene.attachChild(_mute);
		_scene.registerTouchArea(_mute);
		//

		//
		playpause = new AnimatedSprite(xmute - 90, 25, _Rplaypause) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if (_music.isPlaying()) {
						_mute.setCurrentTileIndex(1, 0);
						_music.pause();
						this.setCurrentTileIndex(1, 0);
						_scene.setIgnoreUpdate(true);

					} else {

						_music.play();
						this.setCurrentTileIndex(0, 0);
						_mute.setCurrentTileIndex(0, 0);
						_scene.clearChildScene();
						_scene.setIgnoreUpdate(false);
						_scene.setChildScene(_control);

					}
					return true;
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};
		playpause.setScale(1.5f);
		_scene.attachChild(playpause);
		_scene.registerTouchArea(playpause);

		/*
		 * float xre = (_WIDTH - _Region10.getWidth()) / 2; float yre = (_HEIGHT
		 * - _Region10.getHeight()) / 2; resume = new Sprite(xre, yre,
		 * _Region10) {
		 * 
		 * @Override public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
		 * float pTouchAreaLocalX, float pTouchAreaLocalY) {
		 * _scene.clearChildScene(); _scene.setIgnoreUpdate(false);
		 * _scene.setChildScene(_control); this.setVisible(false); return
		 * super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
		 * pTouchAreaLocalY); }
		 * 
		 * }; resume.setVisible(false); resume.setScale(1.5f);
		 * _scene.attachChild(resume); _scene.registerTouchArea(resume);
		 */
		//
		_rsGame = new Sprite(xmute - 250, 20, _Rrestart) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					restart();
					return true;
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};
		// _rsGame.setScale(0.9f);
		_scene.attachChild(_rsGame);
		_scene.registerTouchArea(_rsGame);
		//
		_Scene2 = new Scene();
		_Scene2.setBackgroundEnabled(false);
		_Scene2.setBackground(new ColorBackground(0.09f, 0.6f, 0.8f));
		//
		float xloser = (_WIDTH - _Rloser.getWidth()) / 2;
		float yloser = (_HEIGHT - _Rloser.getHeight()) / 2;
		_loserGame = new Sprite(xloser, yloser, _Rloser) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {

					this.setVisible(false);
					restart();
				}

				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};

		_Scene2.attachChild(_loserGame);
		_loserGame.setVisible(false);
		_Scene2.registerTouchArea(_loserGame);
		//
		_Scene3 = new Scene();
		_Scene3.setBackgroundEnabled(false);
		_Scene3.setBackground(new ColorBackground(0.09f, 0.6f, 0.8f));
		float xwin = (_WIDTH - _Region11.getWidth()) / 2;
		float ywin = (_HEIGHT - _Region11.getHeight()) / 2;
		_winGame = new Sprite(xwin, ywin, _Region11) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {

					this.setVisible(false);
					Intent intent = new Intent(map3.this, MainActivity.class);
					startActivity(intent);
					finish();
				}

				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}

		};
		_winGame.setScale(2);
		_Scene3.attachChild(_winGame);
		_winGame.setVisible(false);
		_Scene3.registerTouchArea(_winGame);
		//

		//
		restart();
		return _scene;

	}

	public void _addtarget() {
		Random rand = new Random();
		int x = (int) _camera.getWidth() + _TiledTextureRegion.getWidth();
		int minY = _TiledTextureRegion.getHeight();
		int maxY = (int) (_camera.getHeight() - _TiledTextureRegion.getHeight());
		int rangeY = maxY - minY;
		int y = rand.nextInt(rangeY) + minY;

		target = new AnimatedSprite(x, y, _TiledTextureRegion);
		target.animate(new long[] { 200, 200, 200 }, 0, 2, true);
		target.setScale(2f);
		_scene.attachChild(target);

		int minDuration = 4;
		int maxDuration = 10;
		int rangeDuration = maxDuration - minDuration;
		int actualDuration = rand.nextInt(rangeDuration) + minDuration;

		MoveXModifier mod = new MoveXModifier(actualDuration, target.getX(),
				-target.getWidth());
		target.registerEntityModifier(mod);
		addTarget.add(target);

	}

	// dan target
	

	//
	public void _addtarget4() {
		Random rand = new Random();
		int x = (int) _camera.getWidth() + Rbird.getWidth();
		int minY = Rbird.getHeight();
		int maxY = (int) (_camera.getHeight() - Rbird.getHeight());
		int rangeY = maxY - minY;
		int y = rand.nextInt(rangeY) + minY;

		target4 = new AnimatedSprite(x, y, Rbird);
		target4.animate(new long[] { 400,400,400 }, 0, 2, true);
		target4.setScale(0.6f);
		_scene.attachChild(target4);

		int minDuration = 4;
		int maxDuration = 10;
		int rangeDuration = maxDuration - minDuration;
		int actualDuration = rand.nextInt(rangeDuration) + minDuration;

		MoveXModifier mod = new MoveXModifier(actualDuration, target4.getX(),
				-target4.getWidth());
		target4.registerEntityModifier(mod);
		addtarget4.add(target4);

	}

	//
	public void createSpriteTarget4() {
		TimerHandler spriteTimerHandler;
		float Thoigianlap = 15f;// th�?i gian lặp của target

		spriteTimerHandler = new TimerHandler(Thoigianlap, true,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						_addtarget4();
					}
				});

		getEngine().registerUpdateHandler(spriteTimerHandler);
	}

	//
	

	//
	public void createSpriteSpawnTimeHandler() {
		TimerHandler spriteTimerHandler;
		float Thoigianlap = 1f;// th�?i gian lặp của target

		spriteTimerHandler = new TimerHandler(Thoigianlap, true,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						_addtarget();
					}
				});

		getEngine().registerUpdateHandler(spriteTimerHandler);
	}

	public void removeSprite(final AnimatedSprite _sprite, Iterator it) {
		runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				_scene.detachChild(_sprite);
			}
		});
		it.remove();
	}

	// remove dan
	public void removeSprite2(final Sprite _projectile, Iterator it) {
		runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				_scene.detachChild(_projectile);
			}
		});
		it.remove();
	}

	// remove shoottarget
	public void removeSprite3(final Sprite _shoottg, Iterator it) {
		runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				_scene.detachChild(_shoottg);
			}
		});
		it.remove();
	}

	//
	IUpdateHandler detect = new IUpdateHandler() {
		@Override
		public void reset() {
		}

		@Override
		public void onUpdate(float pSecondsElapsed) {

			Iterator<AnimatedSprite> targets = targetLL.iterator();
			AnimatedSprite _target;

			Iterator<Sprite> targets2 = targetll5.iterator();
			Sprite _target2;

			Iterator<Sprite> targets6 = targetLL6.iterator();
			Sprite _target6;

			Iterator<AnimatedSprite> targets4 = targetll4.iterator();
			AnimatedSprite _target4;

			boolean hit = false;
			//

			//
			while (targets.hasNext()) {
				_target = targets.next();

				if (player.collidesWith(_target)) {
					_loserGame.setVisible(true);
					_scene.setChildScene(_Scene2, false, true, true);
					float xno = player.getX() - 30;
					float yno = player.getY() - 45;
					Sprite _no2 = new Sprite(xno, yno, _Region9);
					_scene.attachChild(_no2);

					_sound5.play();

				}

				//
				if (_target.getX() <= -_target.getWidth()) {
					removeSprite(_target, targets);
					// _loi++;
					// _textHighScore.setText("FAIL:" + _loi);
					// if (_loi == 35) {
					// _loserGame.setVisible(true);
					// _scene.setChildScene(_Scene2, false, true, true);
					// }

					break;
				}

				Iterator<Sprite> projectiles = dan.iterator();
				Sprite _projectile;
				while (projectiles.hasNext()) {
					_projectile = projectiles.next();

					if (_projectile.getX() >= _camera.getWidth()
							|| _projectile.getX() <= -_projectile.getWidth()
							|| _projectile.getY() >= _camera.getHeight()
									+ _projectile.getHeight()
							|| _projectile.getY() <= -_projectile.getHeight()) {
						removeSprite2(_projectile, projectiles);
						continue;
					}

					if (_target.collidesWith(_projectile)) {
						// vịt va chạm với đạn bắn ra
						removeSprite2(_projectile, projectiles);
						float xcol = _target.getX();
						float ycol = _target.getY();
						final Sprite _runglong = new Sprite(xcol, ycol, _region14);		
						_scene.attachChild(_runglong);
						addrunglong.add(_runglong);		
						_runglong.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(new AlphaModifier(1.0f, 0.5f, 10),
								new ScaleModifier(19, 0.5f, 1), new RotationModifier(
										(1 +(int)(Math.random()*((5-1)+1))), 0, ((-200) +(int)(Math.random()*((200-(-200))+1)))))));
						MoveModifier m1 = new MoveModifier((2 +(int)(Math.random()*((5-2)+1))),xcol, xcol+((-200) +(int)(Math.random()*((200-(-200))+1))), ycol, ycol+((-200) +(int)(Math.random()*((200-(-200))+1))));
						_runglong.registerEntityModifier(m1);						
								
						
						_scene.registerEntityModifier(new DelayModifier(2,
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										runOnUpdateThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated
												// method
												// stub
												_scene.detachChild(_runglong);
											}
										});
									}
								}));
						
						final Sprite _runglong1 = new Sprite(xcol, ycol, _region14);		
						_scene.attachChild(_runglong1);
						addrunglong.add(_runglong1);
						_runglong1.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(new AlphaModifier(1, 0.5f, 1),
								new ScaleModifier(19, 0.5f, 1))));
						MoveModifier m2 = new MoveModifier((2 +(int)(Math.random()*((5-2)+1))),xcol, xcol+((-200) +(int)(Math.random()*((200-(-200))+1))), ycol, ycol+((-200) +(int)(Math.random()*((200-(-200))+1))));
						_runglong1.registerEntityModifier(m2);						
								
						
						_scene.registerEntityModifier(new DelayModifier(2,
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										runOnUpdateThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated
												// method
												// stub
												_scene.detachChild(_runglong1);
											}
										});
									}
								}));
						
						final Sprite _runglong3 = new Sprite(xcol, ycol, _region14);		
						_scene.attachChild(_runglong3);
						addrunglong.add(_runglong3);
						_runglong3.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(new AlphaModifier(1, 0.5f, 1),
								new ScaleModifier(19, 0.5f, 1), new RotationModifier(
										(1 +(int)(Math.random()*((5-1)+1))), 0, ((-200) +(int)(Math.random()*((200-(-200))+1)))))));
						MoveModifier m3 = new MoveModifier((2 +(int)(Math.random()*((5-2)+1))),xcol, xcol+((-200) +(int)(Math.random()*((200-(-200))+1))), ycol, ycol+((-200) +(int)(Math.random()*((200-(-200))+1))));
						_runglong3.registerEntityModifier(m3);						
								
						
						_scene.registerEntityModifier(new DelayModifier(2,
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										runOnUpdateThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated
												// method
												// stub
												_scene.detachChild(_runglong3);
											}
										});
									}
								}));
						
						final Sprite _runglong4 = new Sprite(xcol, ycol, _region14);		
						_scene.attachChild(_runglong4);
						addrunglong.add(_runglong4);		
						_runglong4.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(new AlphaModifier(1, 0.5f, 1),
								new ScaleModifier(19, 0.7f, 1), new RotationModifier(
										(1 +(int)(Math.random()*((5-1)+1))), 0, ((-200) +(int)(Math.random()*((200-(-200))+1)))))));
						MoveModifier m4 = new MoveModifier((2 +(int)(Math.random()*((5-2)+1))),xcol, xcol+((-200) +(int)(Math.random()*((200-(-200))+1))), ycol, ycol+((-200) +(int)(Math.random()*((200-(-200))+1))));
						_runglong4.registerEntityModifier(m4);						
								
						
						_scene.registerEntityModifier(new DelayModifier(2,
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										runOnUpdateThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated
												// method
												// stub
												_scene.detachChild(_runglong4);
											}
										});
									}
								}));
						
						final Sprite _runglong5 = new Sprite(xcol, ycol, _region14);		
						_scene.attachChild(_runglong5);
						addrunglong.add(_runglong5);		
						_runglong5.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(new AlphaModifier(1, 1f, 1),
								new ScaleModifier(19, 0.5f, 1), new RotationModifier(
										(1 +(int)(Math.random()*((5-1)+1))), 0, ((-200) +(int)(Math.random()*((200-(-200))+1)))))));
						MoveModifier m5 = new MoveModifier((2 +(int)(Math.random()*((5-2)+1))),xcol, xcol+((-200) +(int)(Math.random()*((200-(-200))+1))), ycol, ycol+((-200) +(int)(Math.random()*((200-(-200))+1))));
						_runglong5.registerEntityModifier(m5);						
								
						
						_scene.registerEntityModifier(new DelayModifier(2,
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										runOnUpdateThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated
												// method
												// stub
												_scene.detachChild(_runglong5);
											}
										});
									}
								}));
						// con vit roi
						final Sprite _roi = new Sprite(xcol, ycol, _region13);						
						_scene.attachChild(_roi);
						_roi.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(
								new ScaleModifier(19, 2f, 1), new RotationModifier(
										(1 +(int)(Math.random()*((5-1)+1))), 0, ((-200) +(int)(Math.random()*((200-(-200))+1)))))));
						MoveYModifier mod = new MoveYModifier(2, ycol, _HEIGHT-_roi.getHeight());
						_roi.registerEntityModifier(mod);
						addroi.add(_roi);
						_scene.registerEntityModifier(new DelayModifier(2,
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										runOnUpdateThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated
												// method
												// stub
												_scene.detachChild(_roi);
											}
										});
									}
								}));
						//
						Random random = new Random();
						diembonus = random.nextInt(10);// tạo biến random
						if (diembonus == 1 || diembonus == 9) {
							final Sprite sprite = new Sprite(xcol + 30,
									ycol + 30, Rbonus);
							_scene.attachChild(sprite);
							addBonus.add(sprite);
							_scene.registerEntityModifier(new DelayModifier(2,
									new IEntityModifierListener() {
										@Override
										public void onModifierFinished(
												IModifier<IEntity> pModifier,
												IEntity pItem) {
											// TODO Auto-generated method stub
											runOnUpdateThread(new Runnable() {
												@Override
												public void run() {
													// TODO Auto-generated
													// method
													// stub
													_scene.detachChild(sprite);
												}
											});
										}
									}));
						}

						//
						if (diembonus == 2) {
							final Sprite sprite3 = new Sprite(xcol + 30,
									ycol + 30, Rbonus3);
							_scene.attachChild(sprite3);
							addBonus3.add(sprite3);
							_scene.registerEntityModifier(new DelayModifier(2,
									new IEntityModifierListener() {
										@Override
										public void onModifierFinished(
												IModifier<IEntity> pModifier,
												IEntity pItem) {
											// TODO Auto-generated method stub
											runOnUpdateThread(new Runnable() {
												@Override
												public void run() {
													// TODO Auto-generated
													// method
													// stub
													_scene.detachChild(sprite3);
												}
											});
										}
									}));
						}
						//
						

						hit = true;
						break;
					}
				}

				if (hit) {
					removeSprite(_target, targets);
					if (diembonus == 1 || diembonus == 9) {
						_diem += 2;
					}
					if (diembonus == 2) {
						_diem += 3;
					}
					_diem++;
					_textscore.setText("SCORE:" + _diem);
					_shoot2.play();
					hit = false;
				}

			}
			//
			// handle target4
			while (targets4.hasNext()) {
				_target4 = targets4.next();
				
				if (_target4.getX() <= -_target4.getWidth()) {
					removeSprite(_target4, targets4);
					// _loi++;
					// _textHighScore.setText("FAIL:" + _loi);
					// if (_loi == 35) {
					// _loserGame.setVisible(true);
					// _scene.setChildScene(_Scene2, false, true, true);
					// }

					break;
				}
				if (_target4.getX() <= -_target4.getWidth()) {
					removeSprite(_target4, targets4);
					break;
				}
				//
				Iterator<Sprite> projectiles = dan.iterator();
				Sprite _projectile;
				while (projectiles.hasNext()) {
					_projectile = projectiles.next();

					if (_projectile.getX() >= _camera.getWidth()
							|| _projectile.getX() <= -_projectile.getWidth()
							|| _projectile.getY() >= _camera.getHeight()
									+ _projectile.getHeight()
							|| _projectile.getY() <= -_projectile.getHeight()) {
						removeSprite2(_projectile, projectiles);
						continue;
					}
					//
					if (_target4.collidesWith(_projectile)) {
						removeSprite2(_projectile, projectiles);
						float xcol = _target4.getX();
						float ycol = _target4.getY();
						final Sprite _roi = new Sprite(xcol, ycol, _region15);						
						_scene.attachChild(_roi);
						_roi.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(
								new ScaleModifier(19, 1.2f, 1), new RotationModifier(
										(1 +(int)(Math.random()*((5-1)+1))), 0, ((-200) +(int)(Math.random()*((200-(-200))+1)))))));
						MoveYModifier mod = new MoveYModifier(2, ycol, _HEIGHT-_roi.getHeight());
						_roi.registerEntityModifier(mod);
						addroi.add(_roi);
						_scene.registerEntityModifier(new DelayModifier(2,
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										runOnUpdateThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated
												// method
												// stub
												_scene.detachChild(_roi);
											}
										});
									}
								}));
						//
						
						hit = true;
						break;
					}
					//

				}
				//
				if (hit) {
					removeSprite(_target4, targets4);
					_diem++;
					deadline=deadline+10;
					_textscore.setText("SCORE:" + _diem);
					_shoot2.play();
					hit = false;
				}
			}
			// handle target2
			
			//
			// handle target 3
			while (targets6.hasNext()) {
				_target6 = targets6.next();
				if (player.collidesWith(_target6)) {
				}
				if (_target6.getX() <= -_target6.getWidth()) {
					removeSprite5(_target6, targets6);
					break;
				}
				//
				Iterator<Sprite> projectiles = dan.iterator();
				Sprite _projectile;
				while (projectiles.hasNext()) {
					_projectile = projectiles.next();

					if (_projectile.getX() >= _camera.getWidth()
							|| _projectile.getX() <= -_projectile.getWidth()
							|| _projectile.getY() >= _camera.getHeight()
									+ _projectile.getHeight()
							|| _projectile.getY() <= -_projectile.getHeight()) {
						removeSprite2(_projectile, projectiles);
						continue;
					}
					//
					if (_target6.collidesWith(_projectile)) {
						removeSprite2(_projectile, projectiles);
						float xcol = _target6.getX();
						float ycol = _target6.getY();
						final AnimatedSprite _no = new AnimatedSprite(xcol,
								ycol, _Region6);
						_no.animate(new long[] { 100, 100, 100 }, 0, 2, true);
						_scene.attachChild(_no);
						addNo.add(_no);
						//
						_scene.registerEntityModifier(new DelayModifier(2,
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										runOnUpdateThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated
												// method
												// stub
												_scene.detachChild(_no);
											}
										});
									}
								}));

						hit = true;

						break;
					}
					//

				}

				//
				if (hit) {
					removeSprite5(_target6, targets6);
					_diem -= 10;
					_textscore.setText("SCORE:" + _diem);
					_shoot2.play();
					hit = false;
				}
			}
			while (targets2.hasNext()) {
				_target2 = targets2.next();
				if (player.collidesWith(_target2)) {
					_loserGame.setVisible(true);
					_scene.setChildScene(_Scene2, false, true, true);
					float xno = player.getX() - 30;
					float yno = player.getY() - 45;
					Sprite _no2 = new Sprite(xno, yno, _Region9);
					_scene.attachChild(_no2);
					_sound5.play();
				}
				if (_target2.getX() <= -_target2.getWidth()) {
					removeSprite5(_target2, targets2);
					break;
				}
				//
				Iterator<Sprite> projectiles = dan.iterator();
				Sprite _projectile;
				while (projectiles.hasNext()) {
					_projectile = projectiles.next();

					if (_projectile.getX() >= _camera.getWidth()
							|| _projectile.getX() <= -_projectile.getWidth()
							|| _projectile.getY() >= _camera.getHeight()
									+ _projectile.getHeight()
							|| _projectile.getY() <= -_projectile.getHeight()) {
						removeSprite2(_projectile, projectiles);
						continue;
					}
					//
					if (_target2.collidesWith(_projectile)) {
						removeSprite2(_projectile, projectiles);
						float xcol = _target2.getX();
						float ycol = _target2.getY();
						

						hit = true;

						break;
					}
					//

				}

				//
				
			}
			//
			if (deadline <= 0) {
				_loserGame.setVisible(true);
				_scene.setChildScene(_Scene2, false, true, true);
				float xno = player.getX() - 30;
				float yno = player.getY() - 45;
				Sprite _no2 = new Sprite(xno, yno, _Region9);
				_scene.attachChild(_no2);
				_sound5.play();
			}
			if (_diem < 0) {
				_loserGame.setVisible(true);
				_scene.setChildScene(_Scene2, false, true, true);
				float xno = player.getX() - 30;
				float yno = player.getY() - 45;
				Sprite _no2 = new Sprite(xno, yno, _Region9);
				_scene.attachChild(_no2);
				_sound5.play();
			}
			if (_diem >= 300 && deadline > 0) {
				savingPreferences();
				_winGame.setVisible(true);
				_scene.setChildScene(_Scene3, false, true, true);
			}
			dan.addAll(addDan);
			addDan.clear();

			targetLL.addAll(addTarget);
			addTarget.clear();
			roiLL.add(addroi);
			addroi.clear();
			runglongLL.addAll(addrunglong);
			addrunglong.clear();
			targetLL3.add(addTarget3);
			addTarget3.clear();
			targetll5.addAll(addtarget5);
			addtarget5.clear();
			targetLL6.addAll(addTarget6);
			addTarget6.clear();
			targetll4.addAll(addtarget4);
			addtarget4.clear();
		}
	};

	public void shootProjectile(final float pX, final float pY) {
		int side = 1;
		int offX = (int) (pX - player.getX());
		int offY = (int) (pY - player.getY());
		if (offX <= 0) {
			side = -1;
		}

		final Sprite projectile;
		projectile = new Sprite(player.getX(), player.getY(), _Region3);
		projectile.setScale(0.8f);
		projectile.registerEntityModifier(new SequenceEntityModifier(
				new ParallelEntityModifier(new RotationModifier(3, 0, 360))));
		_scene.attachChild(projectile);

		int realX = (int) (_camera.getWidth() + projectile.getWidth() / 2.0f)
				* side;
		float ratio = (float) offY / (float) offX;
		int realY = (int) ((realX * ratio) + projectile.getY());

		int offRealX = (int) (realX - projectile.getX());
		int offRealY = (int) (realY - projectile.getY());
		float length = (float) Math.sqrt((offRealX * offRealX)
				+ (offRealY * offRealY));
		float velocity = 480.0f / 1.0f; // 480 pixels / 1 sec
		float realMoveDuration = length / velocity;

		MoveModifier mod = new MoveModifier(realMoveDuration,
				projectile.getX(), realX, projectile.getY(), realY);
		projectile.registerEntityModifier(mod);
		addDan.add(projectile);
		_shoot.play();
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
			final float touchX = pSceneTouchEvent.getX();
			final float touchY = pSceneTouchEvent.getY();
			if (touchX > player.getX()) {
				player.animate(new long[] { 200 }, new int[] { 0 }, 10000);
			}
			if (touchX < player.getX()) {
				player.animate(new long[] { 200 }, new int[] { 19 }, 10000);
			}
			shootProjectile(touchX, touchY);
			return true;
		}
		return false;
	}

	public void restart() {

		runOnUpdateThread(new Runnable() {

			@Override
			// to safely detach and re-attach the sprites
			public void run() {
				_diem = 0;
				// _loi = 0;
				deadline = 500;

				_textscore.setText("SCORE:" + 0);
				// _textHighScore.setText("BEST:" + 0);
				_texttime.setText("TIME:" + 500);
				_scene.detachChildren();
				_scene.attachChild(player);
				_scene.attachChild(_textscore);
				_scene.attachChild(_textHighScore);
				_scene.attachChild(_texttime);
				_scene.attachChild(playpause);
				_scene.attachChild(_mute);
				_scene.attachChild(_rsGame);
				_scene.setChildScene(_control);

			}
		});

		// resetting everything
		targetLL.clear();
		addTarget.clear();
		dan.clear();
		addDan.clear();
		addNo.clear();
		addBonus.clear();
		addBonus3.clear();
		targetLL3.clear();
		addTarget3.clear();
		targetll5.clear();
		addtarget5.clear();
		targetLL6.clear();
		addTarget6.clear();
		targetll4.clear();
		addtarget4.clear();
		addroi.clear();
		addrunglong.clear();
		roiLL.clear();
		runglongLL.clear();
	}

	@Override
	public void onPauseGame() {
		if (_music.isPlaying()) {
			_mute.setCurrentTileIndex(1, 0);
			_music.pause();
			playpause.setCurrentTileIndex(1, 0);
			_scene.setIgnoreUpdate(true);
		}
		super.onPauseGame();
	}

	@Override
	public void onResumeGame() {
		restoringPreferences();
		if (!_music.isPlaying()) {
			_music.play();
			playpause.setCurrentTileIndex(0, 0);
			_mute.setCurrentTileIndex(0, 0);
			_scene.clearChildScene();

			_scene.setIgnoreUpdate(false);
			_scene.setChildScene(_control);

		}
		super.onResumeGame();
	}

	//
	public void _addtarget5() {
		Random rand = new Random();
		int y = (int) _camera.getHeight() + Rngaivat.getHeight();
//		int minx = Rngaivat.getWidth();
//		int maxx = (int) _camera.getWidth() - Rngaivat.getWidth();
//		int rangex = maxx - minx;
		int x = Rngaivat.getWidth()+(int)(Math.random()*((_camera.getWidth()-Rngaivat.getWidth())+1));

		Sprite target2 = new Sprite(x, y, Rngaivat);
		target2.registerEntityModifier(new SequenceEntityModifier(
				new ParallelEntityModifier(new AlphaModifier(13, 0, 1),
						new ScaleModifier(20, 0.5f, 1), new RotationModifier(
								21, 0, 360))));
		_scene.attachChild(target2);

		int xmin = 2;
		int xmax = (int) _HEIGHT - Rngaivat.getWidth();
		int xx = xmax - xmin;
		int xmod = rand.nextInt(xx) + xmin;

		MoveModifier mod = new MoveModifier(23, target2.getX(),
				xmod,-target2.getHeight() , target2.getY());
		target2.registerEntityModifier(mod);
		targetll5.add(target2);
	}

	//
	public void _addtarget6() {
		Random rand = new Random();
		int x = (int) _camera.getWidth() + Rtarget6.getWidth();
		int minY = Rtarget6.getHeight();
		int maxY = (int) (_camera.getHeight() - Rtarget6.getHeight());
		int rangeY = maxY - minY;
		int y = rand.nextInt(rangeY) + minY;

		Sprite target2 = new Sprite(x, y, Rtarget6);
		// target2.registerEntityModifier(new SequenceEntityModifier(
		// new ParallelEntityModifier(new AlphaModifier(13, 0, 1),
		// new ScaleModifier(19, 0.5f, 1), new RotationModifier(
		// 21, 0, 360))));
		_scene.attachChild(target2);

		int ymin = 2;
		int ymax = (int) (_WIDTH - Rtarget6.getHeight()) / 2;
		int yy = ymax - ymin;
		int ymod = rand.nextInt(yy) + ymin;

		MoveModifier mod = new MoveModifier(23, target2.getX(),
				-target2.getWidth(), target2.getY(), ymod);
		target2.registerEntityModifier(mod);
		targetLL6.add(target2);
	}

	//
	public void createSpriteTarget5() {
		TimerHandler spriteTimerHandler;
		float Thoigianlap = 7f;// th�?i gian lặp của target

		spriteTimerHandler = new TimerHandler(Thoigianlap, true,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						_addtarget5();
					}
				});

		getEngine().registerUpdateHandler(spriteTimerHandler);
	}

	//
	public void createSpriteTarget6() {
		TimerHandler spriteTimerHandler;
		float Thoigianlap = 5f;// th�?i gian lặp của target

		spriteTimerHandler = new TimerHandler(Thoigianlap, true,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						_addtarget6();
					}
				});

		getEngine().registerUpdateHandler(spriteTimerHandler);
	}

	//
	public void removeSprite5(final Sprite _sprite, Iterator<Sprite> it) {
		runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				_scene.detachChild(_sprite);
			}
		});
		it.remove();
	}

	//
	public void savingPreferences() {
		SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
		SharedPreferences.Editor editor = pre.edit();
		long best = pre.getLong("highscore", 0);
		if (best < deadline) {
			editor.putLong("highscore", deadline);
			editor.commit();
		}

	}

	//
	//
	public void restoringPreferences() {
		SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
		long best = pre.getLong("highscore", 500);
		long i = 500 - best;
		_textHighScore.setText("BEST:" + i);
	}
}
