# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class Test2ndHoneycomb(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.katalon.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_2nd_honeycomb(self):
        driver = self.driver
        driver.get("http://localhost:8080/#/")
        driver.find_element_by_id("signIn").click()
        driver.find_element_by_id("inputMDEx").click()
        driver.find_element_by_id("inputMDEx").clear()
        driver.find_element_by_id("inputMDEx").send_keys("cjk")
        driver.find_element_by_xpath("(//input[@id='inputMDEx'])[2]").clear()
        driver.find_element_by_xpath("(//input[@id='inputMDEx'])[2]").send_keys("123")
        driver.find_element_by_id("login-button").click()
        driver.find_element_by_xpath("(//input[@id='inputMDEx'])[2]").click()
        driver.find_element_by_xpath("//h4[@id='myModalLabel']/span").click()
        driver.find_element_by_id("sign-up").click()
        driver.find_element_by_id("name").click()
        driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("caojinkun")
        driver.find_element_by_id("email").clear()
        driver.find_element_by_id("email").send_keys("627335629@qq.com")
        driver.find_element_by_id("password").clear()
        driver.find_element_by_id("password").send_keys("123")
        driver.find_element_by_id("repeat").clear()
        driver.find_element_by_id("repeat").send_keys("123")
        driver.find_element_by_xpath("//div[@id='myModal']/div/div/div[2]/div/section/form/div[9]/div/button/strong").click()
        driver.find_element_by_xpath("//h4[@id='myModalLabel']/span").click()
        driver.find_element_by_xpath("(//input[@type='text'])[2]").click()
        driver.find_element_by_xpath("(//input[@type='text'])[2]").clear()
        driver.find_element_by_xpath("(//input[@type='text'])[2]").send_keys("ddd")
        driver.find_element_by_xpath("(//input[@type='text'])[2]").send_keys(Keys.ENTER)
        driver.find_element_by_id("search233").click()
        driver.find_element_by_link_text("About us").click()
        driver.find_element_by_xpath("(//button[@type='button'])[4]").click()
        driver.find_element_by_xpath("//div[@id='editor']/div[2]/div[2]/div").click()
        # ERROR: Caught exception [unknown command [editContent]]
        driver.find_element_by_xpath("//div[@id='editorNavBar']/button").click()
        self.assertEqual(u"存储内容进入数据库:<p>##### dasdsadadads</p><p>dsadasasdasd</p><p><br></p>", self.close_alert_and_get_its_text())
        driver.find_element_by_xpath("//div[@id='editorNavBar']/button[3]").click()
        driver.find_element_by_xpath("//textarea[@name='']").click()
        driver.find_element_by_xpath("//textarea[@name='']").clear()
        driver.find_element_by_xpath("//textarea[@name='']").send_keys("# Welcome to HoneyComb\n## dasdasd\n### dada")
        driver.find_element_by_xpath("(//button[@type='button'])[4]").click()
        driver.find_element_by_link_text("Community").click()
        driver.find_element_by_link_text("Explore").click()
        driver.find_element_by_link_text("HONEYCOMB").click()
    
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
