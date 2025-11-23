package ru.autobus.validator;

public class Validator {
    public boolean validateNumber(String numberStr) {
        try {
            int number = Integer.parseInt(numberStr);
            return number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validateModel(String model) {
        return model != null && !model.isEmpty();
    }

    public boolean validateMileage(String mileageStr) {
        try {
            int mileage = Integer.parseInt(mileageStr);
            return mileage >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
