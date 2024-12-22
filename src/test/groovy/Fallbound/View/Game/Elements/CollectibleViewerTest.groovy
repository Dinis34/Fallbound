package Fallbound.View.Game.Elements

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.Collectibles.BulletCountCollectible
import Fallbound.Model.Game.Elements.Collectibles.BulletSpeedCollectible
import Fallbound.Model.Game.Elements.Collectibles.HealthCollectible
import Fallbound.Model.Game.Elements.Collectibles.JumpCollectible
import Fallbound.Model.Game.Elements.Collectibles.MaxHealthCollectible
import Fallbound.Model.Game.Elements.Collectibles.SpeedCollectible
import Fallbound.Model.Vector
import Fallbound.View.Theme
import spock.lang.Specification

class CollectibleViewerTest extends Specification{
    def "test draw health collectible"(){
        given:
            def gui = Mock(GUI)
            def collectible = Mock(HealthCollectible)
            def offset = 3
            def viewer = new CollectibleViewer()
        and:
            collectible.getPosition() >> new Vector(5, 10)
            collectible.getIcon() >> "♥"
            collectible.shouldShowDescription() >> true
            collectible.getDescription() >> "increases health"
            collectible.getCost() >> 5
        when:
            viewer.draw(gui, collectible, offset)
        then:
            1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "♥", Theme.FALLBOUND_GOLD)
            1 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases health", Theme.FALLBOUND_WHITE);
            1 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 5 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }

    def "test draw maxhealth collectible"(){
        given:
            def gui = Mock(GUI)
            def collectible = Mock(MaxHealthCollectible)
            def offset = 3
            def viewer = new CollectibleViewer()
        and:
            collectible.getPosition() >> new Vector(5, 10)
            collectible.getIcon() >> "♡"
            collectible.shouldShowDescription() >> true
            collectible.getDescription() >> "increases max health"
            collectible.getCost() >> 8
        when:
            viewer.draw(gui, collectible, offset)
        then:
            1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "♡", Theme.FALLBOUND_GOLD)
            1 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases max health", Theme.FALLBOUND_WHITE);
            1 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 8 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }

    def "test draw bulletcount collectible"(){
        given:
        def gui = Mock(GUI)
        def collectible = Mock(BulletCountCollectible)
        def offset = 3
        def viewer = new CollectibleViewer()
        and:
        collectible.getPosition() >> new Vector(5, 10)
        collectible.getIcon() >> "|"
        collectible.shouldShowDescription() >> true
        collectible.getDescription() >> "increases bullet count"
        collectible.getCost() >> 3
        when:
        viewer.draw(gui, collectible, offset)
        then:
        1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "|", Theme.FALLBOUND_GOLD)
        1 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases bullet count", Theme.FALLBOUND_WHITE);
        1 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 3 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }

    def "test draw bulletspeed collectible"(){
        given:
        def gui = Mock(GUI)
        def collectible = Mock(BulletSpeedCollectible)
        def offset = 3
        def viewer = new CollectibleViewer()
        and:
        collectible.getPosition() >> new Vector(5, 10)
        collectible.getIcon() >> "⇢"
        collectible.shouldShowDescription() >> true
        collectible.getDescription() >> "increases bullet speed"
        collectible.getCost() >> 8
        when:
        viewer.draw(gui, collectible, offset)
        then:
        1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "⇢", Theme.FALLBOUND_GOLD)
        1 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases bullet speed", Theme.FALLBOUND_WHITE);
        1 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 8 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }

    def "test draw jump collectible"(){
        given:
        def gui = Mock(GUI)
        def collectible = Mock(JumpCollectible)
        def offset = 3
        def viewer = new CollectibleViewer()
        and:
        collectible.getPosition() >> new Vector(5, 10)
        collectible.getIcon() >> "⬆"
        collectible.shouldShowDescription() >> true
        collectible.getDescription() >> "increases jump height"
        collectible.getCost() >> 10
        when:
        viewer.draw(gui, collectible, offset)
        then:
        1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "⬆", Theme.FALLBOUND_GOLD)
        1 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases jump height", Theme.FALLBOUND_WHITE);
        1 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 10 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }

    def "test speed collectible"(){
        given:
            def gui = Mock(GUI)
            def collectible = Mock(SpeedCollectible)
            def offset = 3
            def viewer = new CollectibleViewer()
        and:
            collectible.getPosition() >> new Vector(5, 10)
            collectible.getIcon() >> "⚡"
            collectible.shouldShowDescription() >> true
            collectible.getDescription() >> "increases move speed"
            collectible.getCost() >> 12
        when:
            viewer.draw(gui, collectible, offset)
        then:
            1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "⚡", Theme.FALLBOUND_GOLD)
            1 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases move speed", Theme.FALLBOUND_WHITE);
            1 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 12 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)
    }

    def "test draw speed collectible without description being shown"(){
        given:
            def gui = Mock(GUI)
            def collectible = Mock(SpeedCollectible)
            def offset = 3
            def viewer = new CollectibleViewer()
        and:
            collectible.getPosition() >> new Vector(5, 10)
            collectible.getIcon() >> "⚡"
            collectible.shouldShowDescription() >> false
            collectible.getDescription() >> "increases move speed"
            collectible.getCost() >> 12
        when:
            viewer.draw(gui, collectible, offset)
        then:
        1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "⚡", Theme.FALLBOUND_GOLD)
        0 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases move speed", Theme.FALLBOUND_WHITE);
        0 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 12 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }

    def "test draw jump collectible without description"(){
        given:
            def gui = Mock(GUI)
            def collectible = Mock(JumpCollectible)
            def offset = 3
            def viewer = new CollectibleViewer()
        and:
            collectible.getPosition() >> new Vector(5, 10)
            collectible.getIcon() >> "⬆"
            collectible.shouldShowDescription() >> false
            collectible.getDescription() >> "increases jump height"
            collectible.getCost() >> 10
        when:
            viewer.draw(gui, collectible, offset)
        then:
            1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "⬆", Theme.FALLBOUND_GOLD)
            0 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases jump height", Theme.FALLBOUND_WHITE);
            0 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 10 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)
    }

    def "test draw bulletspeed collectible without description"(){
        given:
            def gui = Mock(GUI)
            def collectible = Mock(BulletSpeedCollectible)
            def offset = 3
            def viewer = new CollectibleViewer()
        and:
            collectible.getPosition() >> new Vector(5, 10)
            collectible.getIcon() >> "⇢"
            collectible.shouldShowDescription() >> false
            collectible.getDescription() >> "increases bullet speed"
            collectible.getCost() >> 8
        when:
            viewer.draw(gui, collectible, offset)
        then:
            1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "⇢", Theme.FALLBOUND_GOLD)
            0 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases bullet speed", Theme.FALLBOUND_WHITE);
            0 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 8 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }

    def "test draw bulletcount collectible without description"(){
        given:
            def gui = Mock(GUI)
            def collectible = Mock(BulletCountCollectible)
            def offset = 3
            def viewer = new CollectibleViewer()
        and:
            collectible.getPosition() >> new Vector(5, 10)
            collectible.getIcon() >> "|"
            collectible.shouldShowDescription() >> false
            collectible.getDescription() >> "increases bullet count"
            collectible.getCost() >> 3
        when:
            viewer.draw(gui, collectible, offset)
        then:
            1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "|", Theme.FALLBOUND_GOLD)
            0 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases bullet count", Theme.FALLBOUND_WHITE);
            0 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 3 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }

    def "test draw health collectible without description"(){
        given:
        def gui = Mock(GUI)
        def collectible = Mock(HealthCollectible)
        def offset = 3
        def viewer = new CollectibleViewer()
        and:
        collectible.getPosition() >> new Vector(5, 10)
        collectible.getIcon() >> "♥"
        collectible.shouldShowDescription() >> false
        collectible.getDescription() >> "increases health"
        collectible.getCost() >> 5
        when:
        viewer.draw(gui, collectible, offset)
        then:
        1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "♥", Theme.FALLBOUND_GOLD)
        0 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases health", Theme.FALLBOUND_WHITE);
        0 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 5 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }

    def "test draw maxhealth collectible without description"(){
        given:
            def gui = Mock(GUI)
            def collectible = Mock(MaxHealthCollectible)
            def offset = 3
            def viewer = new CollectibleViewer()
        and:
            collectible.getPosition() >> new Vector(5, 10)
            collectible.getIcon() >> "♡"
            collectible.shouldShowDescription() >> false
            collectible.getDescription() >> "increases max health"
            collectible.getCost() >> 8
        when:
            viewer.draw(gui, collectible, offset)
        then:
            1 * gui.drawText(new Vector(5, 10).toPosition().applyOffset(offset), "♡", Theme.FALLBOUND_GOLD)
            0 * gui.drawText(new Vector(2, 8).toPosition().applyOffset(offset),"increases max health", Theme.FALLBOUND_WHITE);
            0 * gui.drawText(new Vector(2, 9).toPosition().applyOffset(offset), 8 + " COINS | shoot to purchase", Theme.FALLBOUND_GOLD)

    }







}
