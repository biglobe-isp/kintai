/*
 * This Spock specification was generated by the Gradle 'init' task.
 */
package kintai

import spock.lang.Specification

class MainTest extends Specification {
    def "application has a greeting"() {
        setup:
        def app = new Main()

        when:
        def result = app.greeting

        then:
        result != null
    }
}