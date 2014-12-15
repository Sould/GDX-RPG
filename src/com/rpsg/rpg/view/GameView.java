package com.rpsg.rpg.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rpsg.rpg.system.Setting;
import com.rpsg.rpg.system.TileAtlas;
import com.rpsg.rpg.utils.FontUtil;
import com.rpsg.rpg.utils.GameUtil;
import com.rpsg.rpg.utils.MapControler;
import com.rpsg.rpg.utils.MoveControler;
import com.rpsg.rpg.utils.MsgUtil;
import com.rpsg.rpg.utils.StringUtil;
public class GameView extends IView{
	
	public TileMapRenderer render;
	public Stage stage;
	public TiledMap map;
	public TileAtlas atlas;
	public static boolean inited=false;
	public OrthographicCamera camera;
	
	@Override
	public void init() {
		stage = new Stage(GameUtil.screen_width, GameUtil.screen_height, true);
		camera=(OrthographicCamera) stage.getCamera();
		map=TiledLoader.createMap(Gdx.files.internal(Setting.GAME_RES_MAP + "test/map.tmx"));
		atlas = new TileAtlas(map, Gdx.files.internal(Setting.GAME_RES_MAP 	+ "res"));
		render = new TileMapRenderer(map,atlas,10,10);
		MapControler.init(this);
		inited=true;
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void draw(SpriteBatch batch) {
//		render.render(camera,0,batch);
//		render.render(camera,1,batch);
		MapControler.draw(batch,this);
//		stage.draw();
//		render.render(camera,new int[]{0});
//		render.render(camera,new int[]{0});
//		render.render(camera,new int[]{1});
		FontUtil.draw(batch, "再不显示你妈飞了，wpcap，其实也没占多少内存？我想太多？内存表示有点虚啊hhh，人类为何要互相伤害呢，内存就是不泄露，还可以画多行文字，简直无情", 25, Color.RED, 100, 500, 400);
	}

	@Override
	public void logic() {
		MapControler.logic(this);
		stage.act();
		MoveControler.logic(this);
	}

	@Override
	public void onkeyTyped(char character) {
		
	}
	
	public boolean isPressWalk_l=false,isPressWalk_r=false,isPressWalk_u=false,isPressWalk_d=false;
	public boolean isPressCtrl=false,isPressZ=false;
	
	public void keyDown(int keycode) {
		MoveControler.keyDown(keycode, this);
	}

	public void keyUp(int keycode) {
		MoveControler.keyUp(keycode,this);
	}
}
