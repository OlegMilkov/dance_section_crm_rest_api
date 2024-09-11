package com.example.dance_section_crm_rest_api.controller;

import com.example.dance_section_crm_rest_api.entity.Child;
import com.example.dance_section_crm_rest_api.exeption_handling.NoSuchChildException;
import com.example.dance_section_crm_rest_api.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyRestControllerTest {

    @InjectMocks
    private MyRestController myRestController;

    @Mock
    private ReportService reportService;

    private Child testChild;

    @BeforeEach
    void setUp() {
        testChild = new Child("John", "Doe", "Mr. Doe", LocalDate.of(2010, 1, 1), "+", "+", "+", "+", 2000);
    }

    @Test
    void getAllChildren() {
        List<Child> expectedChildren = new ArrayList<>();
        expectedChildren.add(testChild);
        expectedChildren.add(testChild);

        when(reportService.getAllChildren()).thenReturn(expectedChildren);
        List<Child> actualChildren = myRestController.getAllChildren();
        assertEquals(expectedChildren, actualChildren);
    }

    @Test
    void getChild_whenChildExist() {
        int id = 1;
        when(reportService.getChild(id)).thenReturn(testChild);
        Child actualChild = myRestController.getChild(id);
        assertEquals(testChild, actualChild);
    }

    @Test
    void getChild_whenChildNotExist() {
        int id = 1;
        when(reportService.getChild(id)).thenReturn(null);
        NoSuchChildException thrown = assertThrows(NoSuchChildException.class, () -> {
            myRestController.getChild(id);
        });
        assertEquals("There is no Child with " + id + " in our Database", thrown.getMessage());
    }

    @Test
    void addNewChild() {
        Child newChild = testChild;
        Child actualChild = myRestController.addNewChild(newChild);
        verify(reportService).saveChild(newChild);
        assertEquals(newChild, actualChild);
    }

    @Test
    void updateChild() {
        Child childToUpdate= testChild;
         Child updateChild= myRestController.updateChild(childToUpdate);

         verify(reportService).saveChild(childToUpdate);
         assertEquals(childToUpdate,updateChild);
    }

    @Test
    void deleteChild_whenChildExists() {
        // Arrange: Створюємо ID дитини і тестового дитини, який має бути видалений
        int childId = 1;
        Child child = testChild;

        // Повертаємо тестового дитини, коли викликається метод getChild
        when(reportService.getChild(childId)).thenReturn(child);

        // Act: викликаємо метод deleteChild контролера
        String response = myRestController.deleteChild(childId);

        // Assert: перевіряємо, що deleteChild був викликаний і повернено правильне повідомлення
        verify(reportService).deleteChild(childId);
        assertEquals("Child with ID =1 was deleted", response);
    }

    @Test
    void deleteChild_whenChildDoesNotExist() {
        // Arrange: Створюємо ID дитини, якої немає в базі
        int childId = 1;

        // Налаштовуємо, щоб метод getChild повертав null (тобто дитини немає)
        when(reportService.getChild(childId)).thenReturn(null);

        // Act & Assert: перевіряємо, що викидається NoSuchChildException
        NoSuchChildException thrown = assertThrows(NoSuchChildException.class, () -> {
            myRestController.deleteChild(childId);
        });

        assertEquals("There is no Child with" + childId + " in our Database", thrown.getMessage());
    }

    @Test
    void getChildrenByGroup() {
        // Arrange: створюємо ім'я групи та список дітей, які мають бути повернені
        String groupName = "Group A";
        List<Child> expectedChildren = new ArrayList<>();
        expectedChildren.add(testChild);
        expectedChildren.add(testChild);

        // Налаштовуємо, щоб сервіс повертав цей список для вказаної групи
        when(reportService.getChildrenByGroup(groupName)).thenReturn(expectedChildren);

        // Act: викликаємо метод контролера
        List<Child> actualChildren = myRestController.getChildrenByGroup(groupName);

        // Assert: перевіряємо, що сервіс викликаний з правильним параметром
        verify(reportService).getChildrenByGroup(groupName);

        // Перевіряємо, що фактичний результат відповідає очікуваному
        assertEquals(expectedChildren, actualChildren);
    }


    @Test
    void getNumberOfChildrenInEachGroup() {
        int expectedCount=10;
        String groupName = "Group A";
        String expectedMessage = "Кількість дітей  у группі " + groupName + ": " + expectedCount;

        when(reportService.getNumberOfChildrenInEachGroup(groupName)).thenReturn(expectedCount);

        ResponseEntity<String> responseEntity = myRestController.getNumberOfChildrenInEachGroup(groupName);
        verify(reportService).getNumberOfChildrenInEachGroup(groupName);

        assertEquals(expectedMessage,responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void getAllDebtors() {
        List<Child> expectedDebtors = new ArrayList<>();
        expectedDebtors.add(testChild);
        expectedDebtors.add(testChild);

        when(reportService.getAllDebtors()).thenReturn(expectedDebtors);

        List<Child>actualDebtors =myRestController.getAllDebtors();

        verify(reportService).getAllDebtors();

        assertEquals(expectedDebtors,actualDebtors );
    }

    @Test
    void getChildrenByName() {
        String name="Olivia";
        List<Child>expectedByName= new ArrayList<>();
        expectedByName.add(testChild);
        expectedByName.add(testChild);

        when(reportService.getChildrenByName(name)).thenReturn(expectedByName);

        List <Child>actualChildren =myRestController.getChildrenByName(name);

        verify(reportService).getChildrenByName(name);
        assertEquals(expectedByName,actualChildren );


        // Add implementation
    }

    @Test
    void getAllDistinctGroupNames() {


        List <String> expectedGroupName= new ArrayList<>();
        expectedGroupName.add("1Pro");
        expectedGroupName.add("2Pro");
        expectedGroupName.add("3Pro");

        when(reportService.getAllDistinctGroupNames()).thenReturn(expectedGroupName);

        List<String>actualGroupName= myRestController.getAllDistinctGroupNames();

        verify(reportService).getAllDistinctGroupNames();

        assertEquals(expectedGroupName,actualGroupName);

        // Add implementation
    }

    @Test
    void getAllChildrenSortedByAsc() {
        List<Child>sortedByAsc= new ArrayList<>();
        sortedByAsc.add(testChild);
        sortedByAsc.add(testChild);

        when(reportService.getAllChildrenSortedByGroupAsc()).thenReturn(sortedByAsc);

        List<Child>expectedSortedByAsc=myRestController.getAllChildrenSortedByAsc();

        verify(reportService).getAllChildrenSortedByGroupAsc();

        assertEquals(sortedByAsc,expectedSortedByAsc);
        // Add implementation
    }

    @Test
    void getAllChildrenSortedByDesc() {
        List<Child>sortedByDesc= new ArrayList<>();
        sortedByDesc.add(testChild);
        sortedByDesc.add(testChild);

        when(reportService.getAllChildrenSortedByGroupDesc()).thenReturn(sortedByDesc);

        List<Child>expectedSortedByDesc=myRestController.getAllChildrenSortedByDesc();

        verify(reportService).getAllChildrenSortedByGroupDesc();

        assertEquals(sortedByDesc,expectedSortedByDesc);    }
}
