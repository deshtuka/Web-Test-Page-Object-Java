package project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateTripPage extends BasePage {

    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement textTitleCreateTrip;

    @FindBy(xpath = "//*[@data-ftid='crm_business_trip_businessUnit']")
    private WebElement fieldSubdivision;

    @FindBy(xpath = "//*[@data-ftid='crm_business_trip_company']")
    private WebElement inputHostOrganization;

    @FindBy(xpath = "//*[@id='company-selector-show']")
    private WebElement linkOpenCompanyShow;

    @FindBy(xpath = "//*[@class='company-container']")
    private WebElement rowCompanyContainer;

    @FindBy(xpath = "//*[@class='company-container']//a")
    private WebElement linkCompanyContainer;

    @FindBy(xpath = "//*[@id='select2-drop']//li[2]")
    private WebElement linkToThirdCompanyName;

    @FindBy(xpath = "//*[@data-ftid='crm_business_trip_tasks']//*[text()='Заказ билетов']/../input")
    private WebElement checkboxTripTasks;

    @FindBy(xpath = "//div/input[contains(@id, 'departureCity')]")
    private WebElement inputDepartureCity;
    @FindBy(xpath = "//div/input[contains(@id, 'arrivalCity')]")
    private WebElement inputArrivalCity;
    @FindBy(xpath = "//div/input[contains(@id, 'departureDatePlan')]")
    private WebElement inputDepartureDatePlan;
    @FindBy(xpath = "//div/input[contains(@id, 'returnDatePlan')]")
    private WebElement inputReturnDatePlan;

    @FindBy(xpath = "//body")
    private WebElement fieldBodyToCloseCalendar;
    @FindBy(xpath = "//button[contains(@data-action, 'business_trip_index')]")
    private WebElement btnCreateEntry;

    @FindBy(xpath = "//*[@class='validation-failed']")
    private List<WebElement> textValidationsFailed;

    public void checkTitleCreateTrip() {
        // Проверить наличие на странице заголовка "Создать командировку"
        this.isDisplayed("Создания командировки", textTitleCreateTrip);
        this.isContainsText("Создать командировку", textTitleCreateTrip);
    }

    public void selectSubdivision(String textOption) {
        // В разделе "Подразделение" выбрать "Отдел внутренней разработки"
        this.selectOptionByVisibleText(fieldSubdivision, textOption);
    }

    public void openSelectHostCompany() {
        // В разделе "Принимающая организация" нажать "Открыть список"
        linkOpenCompanyShow.click();
        this.isDisplayed("Укажите организацию", rowCompanyContainer);
    }

    public void selectCompanyFromList() {
        // В разделе "Принимающая организация" выбрать любое значение в "Укажите организацию"
        this.isDisplayed("Укажите организацию", linkCompanyContainer);
        linkCompanyContainer.click();
        this.isDisplayed("Список организаций", linkToThirdCompanyName);
        linkToThirdCompanyName.click();
    }

    public void putCheckboxTicketBooking() {
        // В разделе "Задачи" установить чекбокс на "Заказ билетов"
        checkboxTripTasks.click();
        this.isSelected("Заказ билетов", checkboxTripTasks);
    }

    public void enterCitiesOfDeparture(String value) {
        // В разделе "Сведения" ввести город выбытия
        this.enterValueIntoInputAndCheck("Город выбытия", inputDepartureCity, value);
    }

    public void enterCitiesOfArrival(String value) {
        // В разделе "Сведения" ввести город прибытия
        this.enterValueIntoInputAndCheck("Город прибытия", inputArrivalCity,value );
    }

    public void enterDatesOfDeparture(String value) {
        // В разделе "Сведения" ввести дату выезда
        this.enterValueIntoInputAndCheck("Дата выезда", inputDepartureDatePlan, value);
    }

    public void enterDatesOfArrival(String value) {
        // В разделе "Сведения" ввести дату прибытия
        this.enterValueIntoInputAndCheck("Дата возвращения", inputReturnDatePlan, value);
    }

    public void clickSaveAndClose() {
        // Нажать "Сохранить и закрыть"
        fieldBodyToCloseCalendar.click();
        btnCreateEntry.click();
        loading();
    }

    public void checkMessageError() {
        // Проверить, наличие сообщения "Список командируемых сотрудников не может быть пустым"
        for (WebElement textFailed : textValidationsFailed) {
            this.isContainsText("Список командируемых сотрудников не может быть пустым", textFailed);
        }
    }
}
