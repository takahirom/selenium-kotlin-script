#!/usr/bin/env -S kotlinc -script --
@file:DependsOn("com.github.ajalt:clikt:2.8.0")
@file:DependsOn("org.seleniumhq.selenium:selenium-java:3.141.59")

import com.github.ajalt.clikt.core.CliktCommand
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.interactions.Actions

SeleniumKotlinScript.main(args)

object SeleniumKotlinScript : CliktCommand(name = "selenium-kotlin-script") {
    private lateinit var driver: WebDriver
    override fun run() {
        driver = ChromeDriver(ChromeOptions().apply {
            addArguments("--ignore-ssl-errors=yes")
            addArguments("--ignore-certificate-errors")
        })
//        setBinary(File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"))

        doIt()

        driver.quit()
    }

    private fun doIt() {
        driver.get("https://google.com/")
        driver.manage().window().maximize()
        Actions(driver)
                .moveToElement(driver.findElement(By.name("q")))
                .click()
                .sendKeys("selenium")
                .moveToElement(driver.findElement(By.name("btnK")))
                .click()
                .perform()
    }

}
