package co.wedevx.digitalbank.automation.ui.utils;

import co.wedevx.digitalbank.automation.ui.models.SignUpDetails;
import co.wedevx.digitalbank.automation.ui.models.UpdateProfileDetails;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MockData {
    private FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-US"), new RandomService());

    public String generateRandomSsn() {
        String ssn = String.format("%09d", new Random().nextInt(1000000000));
        //".format()" works as souf
        return ssn;
    }

    public Map<String, String> generateRandomNameAndEmail() {
        String name = fakeValuesService.bothify(new Faker().name().firstName());
        String email = fakeValuesService.bothify(name + "####@gmail.com");
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);
        return data;
    }

    public Map<String, String> generateEmailAndSsn() {
        String email = fakeValuesService.bothify(new Faker().name().firstName()+"###@gmail.com");
        String ssn = String.format("%09d", new Random().nextInt(1000000000));
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("ssn", ssn);
        return data;
    }

    public UpdateProfileDetails generateUpdateData() {
        Faker faker = new Faker();
        String title = faker.options().option("Mr.", "Ms.", "Mrs.");
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String homePhone = faker.phoneNumber().cellPhone();
        String mobilePhone = faker.phoneNumber().cellPhone();
        String workPhone = faker.phoneNumber().cellPhone();
        String address = faker.address().streetAddress();
        String locality = faker.address().city();
        String region = faker.address().stateAbbr();
        String postalCode = faker.address().zipCode();
        String country = faker.address().countryCode();
        return new UpdateProfileDetails(title, firstName, lastName, homePhone,
                mobilePhone, workPhone, address, locality, region, postalCode, country);
    }
}
