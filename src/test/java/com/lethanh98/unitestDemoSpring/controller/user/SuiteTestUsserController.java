package com.lethanh98.unitestDemoSpring.controller.user;

import com.lethanh98.unitestDemoSpring.controller.user.find.FindUserControllerTest;
import com.lethanh98.unitestDemoSpring.controller.user.get.GetUserControllerTest;
import com.lethanh98.unitestDemoSpring.controller.user.post.PostUserControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {GetUserControllerTest.class, PostUserControllerTest.class, FindUserControllerTest.class})
public class SuiteTestUsserController {
}
