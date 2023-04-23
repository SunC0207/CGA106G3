//package CGA106G3.com.memoitemord.DTO;
//
//import org.junit.Test;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import java.util.Date;
//
//public class MemoitemordDetailDTOTest {
//    @Test
//    public void testMemoitemordDetailDTO() {
//
//        MemoitemordDetailDTO dto = new MemoitemordDetailDTO();
//        dto.setMiNo(1);
//        dto.setMiName("item1");
//        dto.setMiPrice(10);
//        dto.setMiCateNo(1);
//        dto.setMiQty(2);
//        dto.setMiDate(new Date());
//        dto.setMiDetailPrice(20);
//        dto.setOrdNo(1);
//        dto.setMembNo(1);
//        dto.setTotalPr(30);
//        dto.setOrdDate(new Date());
//        dto.setOrdSta(1);
//        dto.setPaySta(1);
//        dto.setRec("John Doe");
//        dto.setRecAddr("123 Main St.");
//
//        // Test getters
//        assertEquals(Optional.of(1), dto.getMiNo());
//        assertEquals("item1", dto.getMiName());
//        assertEquals(Optional.of(10), dto.getMiPrice());
//        assertEquals(Optional.of(1), dto.getMiCateNo());
//        assertEquals(Optional.of(2), dto.getMiQty());
//        assertNotNull(dto.getMiDate());
//        assertEquals(Optional.of(20), dto.getMiDetailPrice());
//        assertEquals(Optional.of(1), dto.getOrdNo());
//        assertEquals(Optional.of(1), dto.getMembNo());
//        assertEquals(Optional.of(30), dto.getTotalPr());
//        assertNotNull(dto.getOrdDate());
//        assertEquals(Optional.of(1), dto.getOrdSta());
//        assertEquals(Optional.of(1), dto.getPaySta());
//        assertEquals("John Doe", dto.getRec());
//        assertEquals("123 Main St.", dto.getRecAddr());
//
//        // Test toString
//        String expectedString = "MemoitemordDetailDTO{miNo=1, miName='item1', miPrice=10, miCateNo=1, miQty=2, miDate=" + dto.getMiDate() + ", miDetailPrice=20, ordNo=1, membNo=1, totalPr=30, ordDate=" + dto.getOrdDate() + ", ordSta=1, paySta=1, rec='John Doe', recAddr='123 Main St.'}";
//        assertEquals(expectedString, dto.toString());
//    }
//}
