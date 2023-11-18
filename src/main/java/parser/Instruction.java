package parser;

public class Instruction {
        private Integer pointer;
        private String code;
        private String address;
        public Instruction(int pointer, String code, String address) {
            this.pointer = pointer;
            this.code = code;
            this.address = address;
        }
        public int getPointer() {
            return pointer;
        }
        public void setPointer(int pointer) {
            this.pointer = pointer;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
}
