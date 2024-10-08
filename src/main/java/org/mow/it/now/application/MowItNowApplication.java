package org.mow.it.now.application;

public class MowItNowApplication {
    public static void main(String[] args) {
        MowItNowApplicationFactory factory = new MowItNowApplicationFactory("mow_simulation");
        factory.controller().launchAndPrint();
    }
}
