package Fallbound.GUI

import Fallbound.Model.Position
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame
import spock.lang.Specification

import java.awt.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class LanternaGUITest extends Specification {
    Screen screen
    TextGraphics textGraphics
    Terminal terminal
    Component component
    LanternaGUI gui

    def setup() {
        // Mock the dependencies
        screen = Mock(TerminalScreen)
        textGraphics = Mock(TextGraphics)
        terminal = Mock(AWTTerminalFrame)
        component = Mock(Component)

        // Setup common mock behaviors
        screen.newTextGraphics() >> textGraphics
        terminal.getComponent(0) >> component

        // Create a test instance with constructor injection
        gui = new LanternaGUI(80, 24)
        // Use reflection to set private field
        def screenField = LanternaGUI.class.getDeclaredField("screen")
        screenField.setAccessible(true)
        screenField.set(gui, screen)
    }

    def "drawElement should draw character at correct position with color"() {
        given:
        def position = new Position(10, 5)
        def character = 'X' as char
        def color = "#FF0000"

        when:
        gui.drawElement(position, character, color)

        then:
        1 * textGraphics.setForegroundColor(_)
        1 * textGraphics.putString(10, 6, "X")  // Y + 1 due to implementation detail
    }

    def "drawText should render text at specified position with color"() {
        given:
        def position = new Position(5, 5)
        def text = "Test Text"
        def color = "#00FF00"

        when:
        gui.drawText(position, text, color)

        then:
        1 * textGraphics.setForegroundColor(_)
        1 * textGraphics.putString(5, 5, text)
    }

    def "getNextAction should return copy of active keys"() {
        given:
        def keyEvent = Mock(KeyEvent)
        keyEvent.getKeyCode() >> KeyEvent.VK_SPACE

        def keyAdapter = new KeyAdapter() {
            @Override
            void keyPressed(KeyEvent e) {
                gui.activeKeys.add(e.getKeyCode())
            }
        }
        component.addKeyListener(keyAdapter)

        when:
        keyAdapter.keyPressed(keyEvent)
        def result = gui.getNextAction()

        then:
        result.contains(KeyEvent.VK_SPACE)
        result.size() == 1
    }

    def "getNextSingleAction should clear active keys after returning"() {
        given:
        def keyEvent = Mock(KeyEvent)
        keyEvent.getKeyCode() >> KeyEvent.VK_ENTER

        def keyAdapter = new KeyAdapter() {
            @Override
            void keyPressed(KeyEvent e) {
                gui.activeKeys.add(e.getKeyCode())
            }
        }
        component.addKeyListener(keyAdapter)

        when:
        keyAdapter.keyPressed(keyEvent)
        def result = gui.getNextSingleAction()
        def subsequentResult = gui.getNextAction()

        then:
        result.contains(KeyEvent.VK_ENTER)
        subsequentResult.isEmpty()
    }

    def "clear should clear the screen"() {
        when:
        gui.clear()

        then:
        1 * screen.clear()
    }

    def "refresh should refresh the screen"() {
        when:
        gui.refresh()

        then:
        1 * screen.refresh()
    }

    def "close should close the screen"() {
        when:
        gui.close()

        then:
        1 * screen.close()
    }

    def "key release should remove key from active keys"() {
        given:
        def pressEvent = Mock(KeyEvent)
        def releaseEvent = Mock(KeyEvent)
        pressEvent.getKeyCode() >> KeyEvent.VK_A
        releaseEvent.getKeyCode() >> KeyEvent.VK_A

        def keyAdapter = new KeyAdapter() {
            @Override
            void keyPressed(KeyEvent e) {
                gui.activeKeys.add(e.getKeyCode())
            }

            @Override
            void keyReleased(KeyEvent e) {
                gui.activeKeys.remove(e.getKeyCode())
            }
        }
        component.addKeyListener(keyAdapter)

        when:
        keyAdapter.keyPressed(pressEvent)
        keyAdapter.keyReleased(releaseEvent)
        def result = gui.getNextAction()

        then:
        result.isEmpty()
    }

    def "key release should remove key from active keys"() {
        given:
        def pressEvent = Mock(KeyEvent)
        def releaseEvent = Mock(KeyEvent)
        pressEvent.getKeyCode() >> KeyEvent.VK_A
        releaseEvent.getKeyCode() >> KeyEvent.VK_A

        def keyAdapter = new KeyAdapter() {
            @Override
            void keyPressed(KeyEvent e) {
                gui.activeKeys.add(e.getKeyCode())
            }

            @Override
            void keyReleased(KeyEvent e) {
                gui.activeKeys.remove(e.getKeyCode())
            }
        }
        component.addKeyListener(keyAdapter)

        when:
        keyAdapter.keyPressed(pressEvent)
        keyAdapter.keyReleased(releaseEvent)
        def result = gui.getNextAction()

        then:
        result.isEmpty()
    }
}