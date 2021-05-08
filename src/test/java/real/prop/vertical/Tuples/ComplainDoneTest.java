package real.prop.vertical.Tuples;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ComplainDoneTest {
    @Nested
    class saveTestClass {
        @Test
        @DisplayName(">>Save One Test")
        @Order(1)
        void saveTest() {

        }
    }

    @Nested
    class readOneTestClass{
        @Test
        @DisplayName(">>Read One Test")
        @Order(2)
        void testReadOne(){

        }

    }
    @Nested class ReadAllTestClass{
        @Test
        void testReadAll(){

        }
    }
    @Nested class DeleteTestClass{
        @Test
        void testDelete(){

        }
    }
}