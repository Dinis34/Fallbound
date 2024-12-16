package Fallbound.Controller.Sound;

import Fallbound.Model.Sound.Sound;
import Fallbound.Model.Sound.SoundOption;

public class SoundController {
    private static SoundController soundController;
    private Sound menuMusic;
    private Sound backgroundMusic;
    private Sound bullet;
    private Sound enemyDeath;
    private Sound playerDeath;
    private Sound playerDamage;
    private Sound ding;
    private Sound jump;
    private Sound menuMove;
    private Sound menuSelect;
    private Sound coin;
    private Sound collectible;

    private SoundController() {
        this.menuMusic = new Sound("src/main/resources/sounds/menu_music.wav");
        this.backgroundMusic = new Sound("src/main/resources/sounds/background_music.wav");
        this.bullet = new Sound("src/main/resources/sounds/bullet.wav");
        this.enemyDeath = new Sound("src/main/resources/sounds/enemy_death.wav");
        this.playerDeath = new Sound("src/main/resources/sounds/player_death.wav");
        this.playerDamage = new Sound("src/main/resources/sounds/player_damage.wav");
        this.ding = new Sound("src/main/resources/sounds/ding.wav");
        this.jump = new Sound("src/main/resources/sounds/jump.wav");
        this.menuMove = new Sound("src/main/resources/sounds/menu_move.wav");
        this.menuSelect = new Sound("src/main/resources/sounds/menu_select.wav");
        this.coin = new Sound("src/main/resources/sounds/coin.wav");
        this.collectible = new Sound("src/main/resources/sounds/collectible.wav");
    }

    public static SoundController getInstance() {
        if (soundController == null) {
            soundController = new SoundController();
        }
        return soundController;
    }

    public void playSound(SoundOption option) {
        switch (option) {
            case MENU_MUSIC -> menuMusic.playContinuously();
            case BACKGROUND_MUSIC -> backgroundMusic.playContinuously();
            case BULLET -> bullet.play();
            case ENEMY_DEATH -> enemyDeath.play();
            case PLAYER_DEATH -> playerDeath.play();
            case PLAYER_DAMAGE -> playerDamage.play();
            case DING -> ding.play();
            case JUMP -> jump.play();
            case MENU_MOVE -> menuMove.play();
            case MENU_SELECT -> menuSelect.play();
            case COIN -> coin.play();
            case COLLECTIBLE -> collectible.play();
        }
    }

    public void stopSound(SoundOption option) {
        switch (option) {
            case MENU_MUSIC -> menuMusic.stop();
            case BACKGROUND_MUSIC -> backgroundMusic.stop();
            case BULLET -> bullet.stop();
            case ENEMY_DEATH -> enemyDeath.stop();
            case PLAYER_DEATH -> playerDeath.stop();
            case PLAYER_DAMAGE -> playerDamage.stop();
            case DING -> ding.stop();
            case JUMP -> jump.stop();
            case MENU_MOVE -> menuMove.stop();
            case MENU_SELECT -> menuSelect.stop();
            case COIN -> coin.stop();
            case COLLECTIBLE -> collectible.stop();
        }
    }

    public void resumePlayingMusic() {
        backgroundMusic.resumePlaying();
    }

    public void stopAllSounds() {
        menuMusic.stop();
        backgroundMusic.stop();
        bullet.stop();
        enemyDeath.stop();
        playerDeath.stop();
        playerDamage.stop();
        ding.stop();
        jump.stop();
        menuMove.stop();
        menuSelect.stop();
        coin.stop();
        collectible.stop();
    }

    public void setMenuMusic(Sound menuMusic) {
        this.menuMusic = menuMusic;
    }

    public void setBackgroundMusic(Sound backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public void setBullet(Sound bullet) {
        this.bullet = bullet;
    }

    public void setEnemyDeath(Sound enemyDeath) {
        this.enemyDeath = enemyDeath;
    }

    public void setPlayerDeath(Sound playerDeath) {
        this.playerDeath = playerDeath;
    }

    public void setPlayerDamage(Sound playerDamage) {
        this.playerDamage = playerDamage;
    }

    public void setDing(Sound ding) {
        this.ding = ding;
    }

    public void setJump(Sound jump) {
        this.jump = jump;
    }

    public void setMenuMove(Sound menuMove) {
        this.menuMove = menuMove;
    }

    public void setMenuSelect(Sound menuSelect) {
        this.menuSelect = menuSelect;
    }

    public void setCoin(Sound coin) {
        this.coin = coin;
    }

    public void setCollectible(Sound collectible) {
        this.collectible = collectible;
    }
}