package Fallbound.Controller.Sound

import Fallbound.Model.Sound.Sound
import Fallbound.Model.Sound.SoundOption
import spock.lang.Specification

class SoundControllerTest extends Specification {
    SoundController soundController
    SoundController soundControllerSpy
    def menuMusic = Mock(Sound)
    def backgroundMusic = Mock(Sound)
    def bulletSound = Mock(Sound)
    def enemyDeathSound = Mock(Sound)
    def playerDeathSound = Mock(Sound)
    def playerDamageSound = Mock(Sound)
    def dingSound = Mock(Sound)
    def jumpSound = Mock(Sound)
    def menuMoveSound = Mock(Sound)
    def menuSelectSound = Mock(Sound)
    def coinSound = Mock(Sound)
    def collectibleSound = Mock(Sound)

    def setup() {
        soundController = SoundController.getInstance()
        soundController.setMenuMusic(menuMusic)
        soundController.setBackgroundMusic(backgroundMusic)
        soundController.setBullet(bulletSound)
        soundController.setEnemyDeath(enemyDeathSound)
        soundController.setPlayerDeath(playerDeathSound)
        soundController.setPlayerDamage(playerDamageSound)
        soundController.setDing(dingSound)
        soundController.setJump(jumpSound)
        soundController.setMenuMove(menuMoveSound)
        soundController.setMenuSelect(menuSelectSound)
        soundController.setCoin(coinSound)
        soundController.setCollectible(collectibleSound)
        soundControllerSpy = Spy(soundController)
    }

    def "Should play the menu music"() {
        when:
        soundControllerSpy.playSound(SoundOption.MENU_MUSIC)
        then:
        1 * menuMusic.playContinuously()
    }

    def "Should play the background music"() {
        when:
        soundControllerSpy.playSound(SoundOption.BACKGROUND_MUSIC)
        then:
        1 * backgroundMusic.playContinuously()
    }

    def "Should play the bullet sound"() {
        when:
        soundControllerSpy.playSound(SoundOption.BULLET)
        then:
        1 * bulletSound.play()
    }

    def "Should play the Enemy death sound"() {
        when:
        soundControllerSpy.playSound(SoundOption.ENEMY_DEATH)
        then:
        1 * enemyDeathSound.play()
    }

    def "Should play the Player death sound"() {
        when:
        soundControllerSpy.playSound(SoundOption.PLAYER_DEATH)
        then:
        1 * playerDeathSound.play()
    }

    def "Should play the Player damage sound"() {
        when:
        soundControllerSpy.playSound(SoundOption.PLAYER_DAMAGE)
        then:
        1 * playerDamageSound.play()
    }

    def "Should play the ding"() {
        when:
        soundControllerSpy.playSound(SoundOption.DING)
        then:
        1 * dingSound.play()
    }

    def "Should play the jump sound"() {
        when:
        soundControllerSpy.playSound(SoundOption.JUMP)
        then:
        1 * jumpSound.play()
    }

    def "Should play the Menu move sound"() {
        when:
        soundControllerSpy.playSound(SoundOption.MENU_MOVE)
        then:
        1 * menuMoveSound.play()
    }

    def "Should play the menu select sound"() {
        when:
        soundControllerSpy.playSound(SoundOption.MENU_SELECT)
        then:
        1 * menuSelectSound.play()
    }

    def "Should play the coin sound"() {
        when:
        soundControllerSpy.playSound(SoundOption.COIN)
        then:
        1 * coinSound.play()
    }

    def "Should play the collectable sound"() {
        when:
        soundControllerSpy.playSound(SoundOption.COLLECTIBLE)
        then:
        1 * collectibleSound.play()
    }

    def "Should stop all sounds"() {
        when:
        soundControllerSpy.stopAllSounds()
        then:
        1 * menuMusic.stop()
        1 * backgroundMusic.stop()
        1 * bulletSound.stop()
        1 * enemyDeathSound.stop()
        1 * playerDeathSound.stop()
        1 * playerDamageSound.stop()
        1 * dingSound.stop()
        1 * jumpSound.stop()
        1 * menuMoveSound.stop()
        1 * menuSelectSound.stop()
        1 * coinSound.stop()
        1 * collectibleSound.stop()
    }

    def "Should stop menu music"() {
        when:
        soundControllerSpy.stopSound(SoundOption.MENU_MUSIC)
        then:
        1 * menuMusic.stop()
    }

    def "Should stop background music"() {
        when:
        soundControllerSpy.stopSound(SoundOption.BACKGROUND_MUSIC)
        then:
        1 * backgroundMusic.stop()
    }

    def "Should stop bullet sound"() {
        when:
        soundControllerSpy.stopSound(SoundOption.BULLET)
        then:
        1 * bulletSound.stop()
    }

    def "Should stop enemy death sound"() {
        when:
        soundControllerSpy.stopSound(SoundOption.ENEMY_DEATH)
        then:
        1 * enemyDeathSound.stop()

    }

    def "Should stop player death sound"() {
        when:
        soundControllerSpy.stopSound(SoundOption.PLAYER_DEATH)
        then:
        1 * playerDeathSound.stop()
    }

    def "Should stop player damage sound"() {
        when:
        soundControllerSpy.stopSound(SoundOption.PLAYER_DAMAGE)
        then:
        1 * playerDamageSound.stop()
    }

    def "Should stop ding"() {
        when:
        soundControllerSpy.stopSound(SoundOption.DING)
        then:
        1 * dingSound.stop()
    }

    def "Should stop jump sound"() {
        when:
        soundControllerSpy.stopSound(SoundOption.JUMP)
        then:
        1 * jumpSound.stop()
    }

    def "Should stop menu move sound"() {
        when:
        soundControllerSpy.stopSound(SoundOption.MENU_MOVE)
        then:
        1 * menuMoveSound.stop()

    }

    def "Should stop menu select sound"() {
        when:
        soundControllerSpy.stopSound(SoundOption.MENU_SELECT)
        then:
        1 * menuSelectSound.stop()
    }

    def "Should stop coin sound"() {
        when:
        soundControllerSpy.stopSound(SoundOption.COIN)
        then:
        1 * coinSound.stop()
    }

    def "Should stop collectable sound"() {
        when:
        soundControllerSpy.stopSound(SoundOption.COLLECTIBLE)
        then:
        1 * collectibleSound.stop()
    }


}