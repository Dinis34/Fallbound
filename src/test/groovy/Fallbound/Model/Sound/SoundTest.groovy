package Fallbound.Model.Sound

import spock.lang.Specification

import javax.sound.sampled.Clip

class SoundTest extends Specification {
    def clip = Mock(Clip)

    def "Play"() {
        given:
        def sound = new Sound("src/main/resources/sounds/collectible.wav")

        when:
        sound.setSound(clip)
        sound.play()

        then:
        1 * clip.setFramePosition(0)
        1 * clip.start()
    }

    def "Play while it's already playing"() {
        given:
        def sound = new Sound("src/main/resources/sounds/collectible.wav")
        when:
        sound.setSound(clip)
        clip.isRunning() >> true
        sound.play()
        then:
        1 * clip.setFramePosition(0)
        1 * clip.start()
        1 * clip.stop()

    }

    def "Play continuously"() {
        given:
        def sound = new Sound("src/main/resources/sounds/menu_music.wav")
        when:
        sound.setSound(clip)
        sound.playContinuously()
        then:
        1 * clip.loop(Clip.LOOP_CONTINUOUSLY)
        1 * clip.setFramePosition(0)
        1 * clip.start()
    }

    def "Stop"() {
        given:
        def sound = new Sound("src/main/resources/sounds/collectible.wav")
        when:
        sound.setSound(clip)
        sound.stop()
        then:
        1 * clip.stop()
    }

    def "Resume Playing"() {
        given:
        def sound = new Sound("src/main/resources/sounds/menu_music.wav")
        when:
        sound.setSound(clip)
        sound.resumePlaying()
        then:
        1 * clip.start()
        1 * clip.loop(Clip.LOOP_CONTINUOUSLY)
    }

    def "getSound Test"() {
        given:
        def sound = new Sound("src/main/resources/sounds/menu_music.wav")
        when:
        sound.setSound(clip)
        then:
        sound.getSound() == clip
    }

    def "setSound Test"() {
        given:
        def sound = new Sound("src/main/resources/sounds/menu_music.wav")
        when:
        sound.setSound(clip)
        then:
        sound.getSound() == clip
    }

    def "Invalid Sound Test"() {
        when:
        def sound = new Sound("src/main/resources/highscore.txt")
        then:
        thrown(RuntimeException)
    }

}
