package Fallbound.Controller.Sound;

import Fallbound.Model.Sound.Sound;
import Fallbound.Model.Sound.SoundOption;

public class SoundController {
    private Sound menuMusic;
    private Sound backgroundMusic;
    private Sound bullet;
    private Sound enemyDeath;
    private Sound playerDeath;
    private Sound playerDamage;
    private Sound ding;
    private Sound jump;
    private Sound menuSelect;
    private static SoundController soundController;

    private SoundController() {
        // this.menuMusic = new Sound("src/main/resources/sounds/menu_music.wav");
        // this.backgroundMusic = new Sound("src/main/resources/sounds/background_music.wav");
        // this.bullet = new Sound("src/main/resources/sounds/bullet.wav");
        this.enemyDeath = new Sound("src/main/resources/sounds/enemy_death.wav");
        // this.playerDeath = new Sound("src/main/resources/sounds/player_death.wav");
        // this.playerDamage = new Sound("src/main/resources/sounds/player_damage.wav");
        this.ding = new Sound("src/main/resources/sounds/ding.wav");
        this.jump = new Sound("src/main/resources/sounds/jump.wav");
        // this.menuSelect = new Sound("src/main/resources/sounds/menu_select.wav");
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
            case MENU_SELECT -> menuSelect.play();
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
            case MENU_SELECT -> menuSelect.stop();
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
        menuSelect.stop();
    }
}