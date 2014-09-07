package com.farata.course.mwd.java8

import spock.lang.*

import javax.swing.*

@Title("SwingActionListener Specification")
class SwingActionListenerSpec extends Specification {

    def "main frame should contain 3 components"() {
        setup:
        def app = new SwingActionListener();
        app.setFrame(new JFrame("Test JFrame"))
        app.initUI()
        expect:
        app.frame.getContentPane().componentCount == 3
    }

    def "buttons should have names"() {
        given:
        def app = new SwingActionListener();
        app.setFrame(new JFrame("Test JFrame"))
        app.initUI();

        def pane = app.frame.getContentPane()
        def oldButton = pane.getComponent(0) as JButton
        def newButton = pane.getComponent(1) as JButton
        def console = pane.getComponent(2) as JTextArea
        expect:
        "I'm an Old Java Button".equals oldButton.getText();
        "I'm brand new Java Button".equals newButton.getText();
        "Click a button".equals(console.getText());
    }


    @Shared
    def sharedApp;

    def setupSpec() {
        sharedApp = new SwingActionListener();
        sharedApp.setFrame(new JFrame("Test JFrame"))
        sharedApp.initUI();
    }

    @Unroll
    def "buttons should have names - data driven"() {

        def pane = sharedApp.frame.getContentPane()
        def cmp = pane.getComponent(index)
        expect:
        text.equals cmp.getText();

        where:
        index | text
        0     | "I'm an Old Java Button"
        1     | "I'm brand new Java Button"
        2     | "Click a button"
    }

    def "mock: setSize should be invoked once"() {
        given:
        def app = new SwingActionListener();
        app.setFrame(Mock(JFrame))
        when:
        app.initUI()
        then:
        1 * app.getFrame().setSize(_, _)
    }

    def "mock: frame.addComponent should be invoke 3 times"() {
        given:
        def app = new SwingActionListener();
        app.setFrame(Mock(JFrame))
        when:
        app.initUI()
        then:
        3 * app.getFrame().add(_ as JComponent, _ as String)
    }

    def "stub: frame.addComponent should be invoke 3 times"() {
        given:
        def app = new SwingActionListener();

        // Stubbed JFrame with predefined result of getTitle() method
        app.setFrame(Stub(JFrame) {
            getTitle() >> "Stubbed title"
        })
        when:
        app.initUI()
        then:
        "Stubbed title".equals(app.getTitle())
    }

    def "spy: setSize should be invoked once"() {
        given:
        def app = new SwingActionListener();

        app.setFrame(Spy(JFrame, constructorArgs: ["Stubbed JFrame"]))
        when:
        app.initUI()
        then:
        1 * app.getFrame().setSize(_, _)
    }

    @Ignore("TODO someday")
    def "would be ignored"() {
        expect:
        1 == 1
    }
}
