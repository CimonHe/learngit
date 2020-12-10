import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

//编译的警告只剩下未使用的get和set方法以及未使用的异常类的无参数构造函数和未使用的抽象接口方法
public class work1 {
    public static void main(String[] args) {
        West2FriedChickenRestaurant west2FriedChickenRestaurant = new West2FriedChickenRestaurant();
        west2FriedChickenRestaurant.setBalance(50);//----------设置炸鸡店余额为50
        //-----------------------初始化啤酒果汁列表
        LocalDate tempLocalDate = LocalDate.of(2020, 12, 8);
        west2FriedChickenRestaurant.beerList.add(new Beer("百威", 3, tempLocalDate, (float) 2.56));
        west2FriedChickenRestaurant.beerList.add(new Beer("科罗娜", 5, tempLocalDate, (float) 3.28));
        west2FriedChickenRestaurant.juiceList.add(new Juice("珍珠奶茶", 3, tempLocalDate));
        System.out.println("测试过期函数************************************************************************");
        Beer beer = new Beer("百威", 3, tempLocalDate, (float) 2.56);
        System.out.println("测试2020年12月8号生产的啤酒是否过期:" + beer.isExpired());
        tempLocalDate = LocalDate.of(2020, 9, 10);
        beer = new Beer("百威", 3, tempLocalDate, (float) 2.56);
        System.out.println("测试2020年11月9号生产的啤酒是否过期:" + beer.isExpired());
        System.out.println("测试售卖函数以及异常抛出IngredientSortOutException**************************************");
        try {
            System.out.println("套餐一售卖中……………………");
            west2FriedChickenRestaurant.sellSetMeal("套餐一");
            System.out.println("套餐一售卖成功");
            System.out.println("套餐二售卖中……………………");
            west2FriedChickenRestaurant.sellSetMeal("套餐二");
            System.out.println("套餐二售卖成功");
            System.out.println("套餐三售卖中……………………");
            west2FriedChickenRestaurant.sellSetMeal("套餐三");
            System.out.println("套餐三售卖成功");
        } catch (IngredientSortOutException e) {
            System.out.print("售卖失败（捕获异常）：");
            System.out.println(e.getMessage());
        }
        System.out.println("测试批量进货函数以及异常抛出OverdraftBalanceException*************************************");
        try {
            System.out.println("西二炸鸡店余额为：" + west2FriedChickenRestaurant.getBalance());
            tempLocalDate = LocalDate.of(2020, 12, 8);
            System.out.println("批量进货中……………………");
            west2FriedChickenRestaurant.bulkPurchase(new Juice("珍珠奶茶", 3, tempLocalDate));
            System.out.println("批量进货成功");
            System.out.println("批量进货中……………………");
            west2FriedChickenRestaurant.bulkPurchase(new Juice("珍珠奶茶", 3, tempLocalDate));
            System.out.println("批量进货成功");
        } catch (OverdraftBalanceException e) {
            System.out.print("进货失败（捕获异常）：");
            System.out.println(e.getMessage());
        }
    }
}

abstract class Drinks {
    protected String name;
    protected double cost;
    protected LocalDate producedDate;
    protected int shelfLife;

    public Drinks(String name, double cost, LocalDate producedDate, int shelfLife) {
        this.name = name;
        this.cost = cost;
        this.producedDate = producedDate;
        this.shelfLife = shelfLife;
    }

    @Override
    public abstract String toString();

    boolean isExpired() {
        return !LocalDate.now().minusDays(shelfLife).isBefore(producedDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drinks drinks = (Drinks) o;
        return name.equals(drinks.name);
    }
}

class Beer extends Drinks {
    float concentration;

    public Beer(String name, double cost, LocalDate producedDate, float concentration) {
        super(name, cost, producedDate, 30);
        this.concentration = concentration;
    }

    @Override
    public String toString() {
        return "啤酒{" +
                "名字：'" + name + '\'' +
                ", 花费：" + cost +
                ", 生产日期：" + producedDate +
                ", 保质期：" + shelfLife +
                ", 酒精度：" + concentration +
                '}';
    }
}

class Juice extends Drinks {
    public Juice(String name, double cost, LocalDate producedDate) {
        super(name, cost, producedDate, 2);
    }

    @Override
    public String toString() {
        return "果汁{" +
                "名字：'" + name + '\'' +
                ", 成本：" + cost +
                ", 生产日期：" + producedDate +
                ", 保质期：" + shelfLife +
                '}';
    }
}

class SetMeal {
    String name;
    double price;
    String friedChickenName;
    Drinks drinks;

    public SetMeal(String name, double price, String friedChickenName, Drinks drink) {
        this.name = name;
        this.price = price;
        this.friedChickenName = friedChickenName;
        this.drinks = drink;
    }

    public SetMeal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFriedChickenName() {
        return friedChickenName;
    }

    public void setFriedChickenName(String friedChickenName) {
        this.friedChickenName = friedChickenName;
    }

    public Drinks getDrink() {
        return drinks;
    }

    public void setDrink(Drinks drink) {
        this.drinks = drink;
    }

    @Override
    public String toString() {
        return "套餐{" +
                "名字：" + name + '\'' +
                ", 价格：" + price +
                ", 炸鸡名：'" + friedChickenName + '\'' +
                ", 饮料：" + drinks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetMeal setMeal = (SetMeal) o;
        return Objects.equals(name, setMeal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

interface FriedChickenRestaurant {
    void sellSetMeal(String name);

    void bulkPurchase(Drinks drinks);
}

class IngredientSortOutException extends RuntimeException {
    public IngredientSortOutException() {
    }

    public IngredientSortOutException(String message) {
        super(message);
    }
}

class OverdraftBalanceException extends RuntimeException {
    public OverdraftBalanceException() {
    }

    public OverdraftBalanceException(String message) {
        super(message);
    }

}

class West2FriedChickenRestaurant implements FriedChickenRestaurant {
    private int balance;
    LinkedList<Beer> beerList = new LinkedList<>();
    LinkedList<Juice> juiceList = new LinkedList<>();
    static ArrayList<SetMeal> setMealList = new ArrayList<>();
    //********************************************************************************************************************
    //由于ArrayList查询快  而增加删除慢 所以适用于存放 初始化不改变、并且需要查询的套餐列表
    //而由于LinkedList查询慢  而增加删除快 所以适用于经常增删的饮料列表
    //********************************************************************************************************************
    public static final int BULLFINCHES = 10;//一次进货饮料的数量

    static {
        LocalDate tempLocalDate = LocalDate.of(2020, 11, 20);
        setMealList.add(new SetMeal("套餐一", 30, "韩式炸鸡", new Beer("百威", 3, tempLocalDate, (float) 2.56)));
        tempLocalDate = LocalDate.of(2020, 11, 25);
        setMealList.add(new SetMeal("套餐二", 30, "美式炸鸡", new Beer("科罗娜", 5, tempLocalDate, (float) 3.28)));
        setMealList.add(new SetMeal("套餐三", 30, "美式炸鸡", new Juice("珍珠奶茶", 5, tempLocalDate)));
    }

    private void use(Beer beer) throws IngredientSortOutException {
        beerList.removeIf(beer1 -> beer.isExpired());//-----------先移除过期饮料
        if (beerList.contains(beer)) {
            beerList.remove(beer);
        } else
            throw new IngredientSortOutException(beer.name + "啤酒卖完");
    }

    private void use(Juice juice) throws IngredientSortOutException {
        juiceList.removeIf(juice1 -> juice.isExpired());//-----------先移除过期饮料
        if (juiceList.contains(juice)) {
            juiceList.remove(juice);
        } else
            throw new IngredientSortOutException(juice.name + "果汁卖完");
    }

    @Override
    public void sellSetMeal(String name) throws IngredientSortOutException {
        SetMeal setMeal = new SetMeal(name);
        setMeal = setMealList.get(setMealList.indexOf(setMeal));
        if (setMeal.drinks instanceof Beer)
            this.use((Beer) setMeal.drinks);
        else
            this.use((Juice) setMeal.drinks);
    }

    @Override
    public void bulkPurchase(Drinks drinks) throws OverdraftBalanceException {
        if (drinks instanceof Beer) {
            if (balance >= drinks.cost * BULLFINCHES) {
                for (int i = 1; i <= BULLFINCHES; i++)
                    beerList.add((Beer) drinks);
                balance -= drinks.cost * BULLFINCHES;
            } else
                throw new OverdraftBalanceException("进货费用超出拥有余额");
        }

        if (drinks instanceof Juice) {
            if (balance >= drinks.cost * BULLFINCHES) {
                for (int i = 1; i <= BULLFINCHES; i++)
                    juiceList.add((Juice) drinks);
                balance -= drinks.cost * BULLFINCHES;
            } else
                throw new OverdraftBalanceException("进货费用超出拥有余额");
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

