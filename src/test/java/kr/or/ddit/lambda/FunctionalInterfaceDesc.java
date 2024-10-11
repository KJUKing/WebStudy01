package kr.or.ddit.lambda;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JavaScript
 * 1. 함수(일급함수 혹은 일급객체형 함수) : 일급객체 함수가 익명 형태로 자동 생성
 *          일급객체란? 참조 주소가 있고, 해당 참조를 변수를 통해 받을 수 있고,
 *          또 다른 함수의 호출시 아규먼트로 전달할 수 있고, 반환값의 형태로도 활용 할 수 있음.
 *
 *  function case1_1(){...} //함수선언식
 *  let case1_2 = function(){...} //함수표현식
 *  let case1_3 = () => {}
 * 2. 메소드
 *  let obj = {
 *      case2_1:funtion(){...}
 *      , case2_2:()=>{...}
 *  }
 * 3.
 *  class Component{
 *      method1(){ 자바스크립트는 리턴타입이 존재하지않는다
 *
 *      }
 *  }
 *  let comp = new Component();
 *  comp.method1();
 *
 *  Java에서 함수형 프로그래밍의 지원
 *  1.8 이전까지는 일급함수가 존재하지 않아서, 익명객체를 통해 메소드 하나를 구현하고 있는 객체의 형태로 메소드를 전달해야 했음
 *  1.8 이후부터 lambda 표현식으로 일금함수를 표현할 수 있음. --> 차후 컴파일시에 익명 객체로 래핑됨.
 *  일급함수로 표현될 수 있는 익명객체는 함수형 인터페이스만 가능함.
 *  함수형 인터페이스란? 인터페이스가 단 하나의 추상메소드를 갖는 경우에 대한 표현.
 *
 *  자주 활용되는 functional interface타입의 종류
 *  1. 생성형 FI (Supplier) : 인자가 없고, 하나의 반환값이 있음.
 *  2. 필터링 FI (Predicate) : 하나의 인자가 있고 반환값이 boolean.
 *  3. 처리형 FI (Function) : 하나의 인자가 있고 반환값이 있음.
 *  4. 소비형 FI (Cunsumer) :  하나의 인자가 있고 반환값이 void.
 *
 */
public class FunctionalInterfaceDesc {

    //스트림 생성과정
    Stream<Integer> fiveNumberBranding(Supplier<Integer> oem){
        Integer[] brand = new Integer[5];
        Arrays.setAll(brand, n -> oem.get());
        return Arrays.stream(brand);
    }

    @Test
    void testStream() {
        fiveNumberBranding(() -> new Random().nextInt())
                .filter(n -> n%2==1)
                .map(n ->String.format("%d is odd number", n))
                .forEach(System.out::println);
                    // s ->System.out.println(s); 메소드 레퍼런스를사용하려면 s가 또다른 메소드에
                    //                          똑같은 인자를 보내는거면 사용할수있다 (조건임)
                    // System.out::println 파라미터를 가지고 아무것도안하고 그냥 보낼때만 이거로 변환가능
    }

    @Disabled
    @Test
    void testLambda(){
       Supplier<Integer> supplier = () -> new Random().nextInt(); //생성형
       Predicate<Integer> predicate = n -> n%2==1; //필터링
       Function<Integer, String> function = n -> String.format("%d is odd number", n); //처리형
       Consumer<String> consumer = s -> System.out.println(s); //소비형

        //하나하나 다 담아둔경우
        Optional.of(supplier.get())
                //생성형
                .filter(predicate)
                //필터링
                .map(function)
                //처리형
                .ifPresent(consumer);
                //소비형

        //바로사용
        Optional.of(new Random().nextInt())
                .filter(n -> n % 2 == 1)
                .map(n -> String.format("%d is odd number", n))
                .ifPresent(s ->System.out.println(s));

        //변수에 한번 담아두고 쓰는경우
        Optional<Integer> optionalNumber = Optional.of(new Random().nextInt());

        optionalNumber.filter(n -> n % 2 == 1)
                .map(n -> String.format("%d is odd number", n))
                .ifPresent(s -> System.out.println(s));

    }

    @Disabled
    @Test
    void test() {
        File folder = new File("D:\\00.medias\\movies");
        folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
        });
        folder.list((d, n)-> false); //{return false;} 이걸 sugar syntax로바꿀수있다
    }

}

interface NonFI{
    void method1(); // 인터페이스하나에 추상메소드 단 하나만있으면 함수형인터페이스
    //거기에 애노테이션까지 선언하면리얼완벽
    void mehtod2(); //두개있으면 대표하는 메소드를몰라서 함수형 인터페이스 선언불가
}

@FunctionalInterface // 메소드가있어도 추상메소드가 하나 구체메소드는 몇개가있든 상관이없다
interface FICase{
    void method1();
    default void mehtod2(){
        System.out.println("기본 메소드");
    };
}
