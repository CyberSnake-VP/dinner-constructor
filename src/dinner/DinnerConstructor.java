package dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {

    // хранилище блюд: ключ — тип блюда (например, "Суп"), значение — список названий блюд этого типа
    HashMap<String, ArrayList<String>> dinnersByType = new HashMap<>();

    //этот вспомогательный класс поможет сделать произвольные сочетания блюд
    Random random = new Random();

    //в этом методе мы добавляем компонент в подборку
    public void addNewDish(String dishType, String dishName) {
        ArrayList<String> dishesForType; //переменная для списка блюд

        //здесь мы должны проверить, содержит ли наше хранилище такое блюдо по ключу
        if (checkType(dishType)) {
            dishesForType = dinnersByType.get(dishType); //если мы уже работали с этим типом - используем существующий список
        } else {
            dishesForType = new ArrayList<>(); //для нового типа блюд создаём пустой список компонентов.
            dinnersByType.put(dishType, dishesForType); //запоминаем новый список в хранилище
        }

        //независимо от того, новый это список или существующий - добавим в него конкретное блюдо
        dishesForType.add(dishName);
    }

    //метод для генерирования списка вариантов блюд
    public ArrayList<ArrayList<String>> generateCombos(int comboNumber, ArrayList<String> dishTypes) {
        ArrayList<ArrayList<String>> combos = new ArrayList<>(); //пустой список для хранения получившихся комбинаций блюд
        for (int i = 0; i <= comboNumber; i++) {
            ArrayList<String> combo = generateCombo(dishTypes); //одна комбинация блюд генерируется в отдельном методе
            combos.add(combo);
        }
        return combos;
    }


    //метод для проверки дубликатов блюд
    public boolean checkType(String type) {
        return dinnersByType.containsKey(type); //если хранилище уже содержит такое блюдо - вернём true
    }

    //метод для проверки существования блюд
    public boolean checkExistedDish() {
        return dinnersByType.isEmpty();
    }

    //метод для генерирования одной комбинации блюд
    private ArrayList<String> generateCombo(ArrayList<String> dishTypes) {

        // создаем список для хранения генерации блюд
        ArrayList<String> selectedDishes = new ArrayList<>();

        // в цикле пробегаемся по списку типов, которые передавал пользователь для комбинации
        for (String dishType : dishTypes) {
            //достаём из хранилища варианты блюд по типу
            ArrayList<String> availableDishes = dinnersByType.get(dishType);
            //получим произвольное блюдо
            String selectedDish = getRandomDish(availableDishes);
            //добавим блюдо в подборку комбинацию
            selectedDishes.add(selectedDish);
        }
        return selectedDishes;
    }

    private String getRandomDish(ArrayList<String> availableDishes) {
        //получаем общее количество доступных блюд этого типа
        int numberOfDishesForType = availableDishes.size();
        //генерируем случайное число от 0 до (кол-во блюд - 1), чтобы выбрать случайное блюдо
        int dishIndex = random.nextInt(numberOfDishesForType);
        //выберем произвольное блюдо по индексу
        return availableDishes.get(dishIndex);
    }

}
