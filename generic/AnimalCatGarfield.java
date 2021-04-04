//import java.util.ArrayList;
//import java.util.List;
//class Animal{}
//class Cat extends Animal{}
//class Garfield extends Cat{}
//
///**
// * <? extends T> 是Get First，适用于，消费集合元素的场景；<? super T>是Put First，适用于，生产集合元素为主的场景。
// * <? extends T> 的场景是put功能受限，而<? super T>的场景是get功能受限。
// * <? extends T> 可以赋值给任意T及T的子类集合，上界为T，取出来的类型带有泛型限制，向上强制转型为T。null 可以表示任何类型，所以null除外，任何元素都不得添加进<? extends T>集合内。
// * <? super T> 可以复制T及任何T的父类集合，下界为T。再生活中，投票选举类似于<? super T>的操作。选举投票时，你只能往里投票，取数据时，根本不知道时是谁的票，相当于泛型丢失
// */
//
////用动物，猫，加菲猫的继承关系说明extends与super在集合中的意义
//public class AnimalCatGarfield {
//    public static void main(String[] args) {
//        //第一段：声明第三个依次继承的集合：Object>动物>猫>加菲猫  三个泛型集合可以理解为三个不同的笼子
//        List<Animal> animal = new ArrayList<Animal>();        //动物
//        List<Cat> cat = new ArrayList<Cat>();				  //猫
//        List<Garfield> garfield = new ArrayList<Garfield>();  //加菲猫
//
//        animal.add(new Animal());
//        cat.add(new Cat());
//        garfield.add(new Garfield());
//
//        //第二段：测试赋值操作  以Cat为核心，因为它既有子类又有父类
//        //下行编译出错。只能赋值Cat或Cat子类集合
//        // List<? extends Cat> extendsCatFromAnimal = animal;
//        List<? super Cat> superCatFromAnimal = animal;
//
//        List<? extends Cat> extendsCatFromCat = cat;
//        List<? super Cat> superCatFromCat = cat;
//
//        List<? extends Cat> extendsCatFromGarfield = garfield;
//        //下行编译出错。只能赋值Cat或着Cat父类集合
//        List<? super Cat> superCatFromGarfield = garfield;
//
//        //第三段：测试add方法
//        //下面三行中所有的<? extends T>都无法进行add操作，编译出错
//        extendsCatFromCat.add(new Animal());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new Garfield());
//
//        //下行编译出错。只能添加Cat或者Cat的子类集合。
//        superCatFromCat.add(new Animal());
//        superCatFromCat.add(new Cat());
//        superCatFromCat.add(new Garfield());
//
//        //第四段：测试get方法
//        //所有的super操作能够返回元素，但是泛型丢失，只能返回object对象
//        Object object1 = superCatFromCat.get(0);
//        Animal object = superCatFromCat.get(0);//Type mismatch: cannot convert from capture#8-of ? super Cat to Animal
//        Cat object3 = superCatFromCat.get(0);//
//        //以下extends操作能够返回元素
//        Animal catExtends3 = extendsCatFromCat.get(0);
//        Object catExtends2 = extendsCatFromCat.get(0);
//        Cat catExtends1 = extendsCatFromCat.get(0);
//        //下行编译错误。虽然Cat集合从Garfield赋值而来，但类型擦除后，是不知道的
//        Garfield cat2 = extendsCatFromGarfield.get(0);
//    }
//}
//
