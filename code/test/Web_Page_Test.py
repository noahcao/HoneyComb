# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class HoneyCombTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.katalon.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_honey_comb(self):
        driver = self.driver
        driver.get("http://combsite.site/#/")
        driver.find_element_by_link_text("Sign in").click()
        driver.find_element_by_xpath("//input[@type='text']").click()
        driver.find_element_by_xpath("//input[@type='text']").clear()
        driver.find_element_by_xpath("//input[@type='text']").send_keys("cjk")
        driver.find_element_by_xpath("//input[@type='password']").click()
        driver.find_element_by_xpath("//input[@type='password']").clear()
        driver.find_element_by_xpath("//input[@type='password']").send_keys("123")
        driver.find_element_by_id("login-button").click()
        self.assertEqual("success", self.close_alert_and_get_its_text())
        driver.find_element_by_link_text("My Profile").click()
        driver.find_element_by_xpath("//img[@alt='70x70']").click()
        driver.find_element_by_id("icon").clear()
        driver.find_element_by_id("icon").send_keys(u"C:\\fakepath\\屏幕快照 2018-07-13 上午10.09.58.png")
        driver.find_element_by_link_text("cjk").click()
        driver.find_element_by_link_text("Sign out").click()
        # ERROR: Caught exception [ERROR: Unsupported command [doubleClick | link=Sign out | ]]
        driver.find_element_by_link_text("Sign out").click()
        driver.find_element_by_id("sign-up").click()
        driver.find_element_by_id("name").click()
        driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("cjkkkkk")
        driver.find_element_by_id("email").click()
        driver.find_element_by_id("email").clear()
        driver.find_element_by_id("email").send_keys("627335629@qq.com")
        driver.find_element_by_id("password").click()
        driver.find_element_by_id("password").clear()
        driver.find_element_by_id("password").send_keys("123")
        driver.find_element_by_id("repeat").click()
        driver.find_element_by_id("repeat").clear()
        driver.find_element_by_id("repeat").send_keys("123")
        driver.find_element_by_xpath("//div[@id='myModal']/div/div/div[2]/div/section/form/div[9]/div/button/strong").click()
        driver.find_element_by_id("password").click()
        driver.find_element_by_id("password").clear()
        driver.find_element_by_id("password").send_keys("cjk123")
        driver.find_element_by_id("repeat").click()
        driver.find_element_by_id("repeat").clear()
        driver.find_element_by_id("repeat").send_keys("cjk123")
        driver.find_element_by_xpath("(//button[@type='button'])[2]").click()
    
    def is_element_present(self, how, what):
        try: self.driver.find_element(by=how, value=what)
        except NoSuchElementException as e: return False
        return True
    
    def is_alert_present(self):
        try: self.driver.switch_to_alert()
        except NoAlertPresentException as e: return False
        return True
    
    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to_alert()
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally: self.accept_next_alert = True
    
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()
