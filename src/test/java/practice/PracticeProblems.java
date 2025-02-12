package practice;

import org.openqa.selenium.WebDriver;

/**
 * Example of Page Object Model (POM) of the Google home page.
 * 
 * All elements of the page that will be used must be instantiated with their respectives xpath. 
 * 
 * @author ejunior
 *
 */
public class PracticeProblems {
	private WebDriver driver;
	
	private String txtSearch = "//input[@name='q']";
	private String btnSearch = "//input[@name='btnK']";

	// /**
	//  * Constructor of the page. Initialize the Page Factory objects. 
	//  * 
	//  * @param driver
	//  */
	// public PracticeProblems(WebDriver driver) {
	// 	super(driver);
	// 	this.driver = driver;
	// }

	// /**
	//  * Performs a simple google search and return the next page.
	//  * 
	//  * @param query
	//  */
	// public GoogleResultsPage searchFor(String query) {
	// 	SeleniumUtils.waitForElement(driver, txtSearch).sendKeys(query);
	// 	SeleniumUtils.waitForElementToBeClickable(driver, btnSearch).click();
		
	// 	return new GoogleResultsPage(driver);
	// }
	public static void main(String[] args) {
		//Start of duplicate, we have the input array
		int[] nums = {1,1,2};
		int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
		
		//We call the duplicate function
		int k = uniqueNumbers(nums2);

		//Output
		for(int i=0;i<k;i++){
			System.out.print(nums2[i]);
		}
	}

	public static int uniqueNumbers(int[] nums){
		//We want to get a helper=0, this is the start of the list
        int helper=0;
        //One loop that traverses the list only one time
        //Start point i should start at the second position
        for(int i=1; i<nums.length ;i++){
            //We compare the previous and next element, if it is not equal to the next element we proceed
            if(nums[i]!=nums[helper]){
                //We add one to the helper to skip the first elementalidad as it's always unique
                helper++;
                //We rewrite the element on the list
                nums[helper]=nums[i];
            }
        }
        //We return 
		return helper+1;
	}
}
