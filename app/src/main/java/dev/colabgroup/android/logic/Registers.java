package dev.colabgroup.android.logic;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
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
        private String surnames;
        private Integer dni;
        private Integer celphoneNumber;

        public Register() {
            this.id = generateUUID();
        }

        public Register(String names, String surnames, Integer dni, Integer celphoneNumber) {
            this.id = generateUUID();
            this.names = names;
            this.surnames = surnames;
            this.dni = dni;
            this.celphoneNumber = celphoneNumber;
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

        public String getSurnames() {
            return surnames;
        }

        public void setSurnames(String surnames) {
            this.surnames = surnames;
        }

        public Integer getDni() {
            return dni;
        }

        public void setDni(Integer dni) {
            this.dni = dni;
        }

        public Integer getCelphoneNumber() {
            return celphoneNumber;
        }

        public void setCelphoneNumber(Integer celphoneNumber) {
            this.celphoneNumber = celphoneNumber;
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
