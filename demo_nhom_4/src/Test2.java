
    public class Test2 implements Comparable<HocSinh> {
        private String ten;
        private double diemToan;
        private double diemVan;

        // constructor, getter, setter

        @Override
        public int compareTo(HocSinh hs) {
            // so sánh theo điểm trung bình của học sinh
            double diemTB1 = (this.diemToan + this.diemVan) / 2;
            double diemTB2 = (hs.getDiemToan() + hs.getDiemVan()) / 2;
            if (diemTB1 < diemTB2) {
                return -1;
            } else if (diemTB1 > diemTB2) {
                return 1;
            } else {
                return 0;
            }
        }
    }
