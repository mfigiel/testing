package groovy

class groovy extends GroovyTestCase {
    void testNotPass() {
        def firstAppDisposableIncome = 50
        def requestedAmountValue = 5000
        def duration = 84

        float inputValue = firstAppDisposableIncome - (requestedAmountValue / duration)

        println(inputValue)

        println(84.getClass())

        println(84.0.getClass())


        println((5000/84).getClass())
    }
}
