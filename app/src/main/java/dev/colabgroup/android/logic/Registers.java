package dev.colabgroup.android.logic;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Registers {
    private List<Register> registers;
    private final static Registers instance = new Registers();
    private static Long UUID = 0L;

    private static Long generateUUID () {
        return UUID++;
    }

    public static class Register {
        private Long id;
        private String names;
        private Integer dni;
        private Integer age;
        private String address;
        private String course;

        public Register() {
            this.id = generateUUID();
        }

        public Register(String names, Integer dni, Integer age, String address, String course) {
            this.id = generateUUID();
            this.names = names;
            this.dni = dni;
            this.age = age;
            this.address = address;
            this.course = course;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
        }

        public Integer getDni() {
            return dni;
        }

        public void setDni(Integer dni) {
            this.dni = dni;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }
    }

    private Registers () {};

    public static Registers getInstance () {
        return instance;
    }

    public List<Register> getRegisters () {
        return registers;
    }

    public void appendRegister (Register register) {
        registers.add(register);
    }

    public void removeRegister (Register register) {
        registers.remove(register);
    }

    public boolean updateRegister (Register register) {
        Long id = register.getId();
        AtomicBoolean flag = new AtomicBoolean(false);

        registers.forEach((reg) -> {
            if (id.equals(reg.id)) {
                int pos = registers.indexOf(register);

                registers.set(pos, register);

                flag.set(true);
            }
        });

        return flag.get();
    }
}
