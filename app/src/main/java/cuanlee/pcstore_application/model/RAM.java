package cuanlee.pcstore_application.model;

/**
 * Created by CuanL on 29/08/2016.
 */
public class RAM {
    private Long id;
    private String code;
    private String description;
    private String memorySize;
    private double voltage;
    private String memoryConfiguration;
    private Integer stock;
    private Integer activeStock;

    public RAM() {
    }

    public RAM(String code, String description, String memorySize, double voltage, String memoryConfiguration, Integer stock, Integer activeStock) {
        this.code = code;
        this.description = description;
        this.memorySize = memorySize;
        this.voltage = voltage;
        this.memoryConfiguration = memoryConfiguration;
        this.stock = stock;
        this.activeStock = activeStock;
    }

    public RAM(Long id, String code, String description, String memorySize, double voltage, String memoryConfiguration, Integer stock, Integer activeStock) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.memorySize = memorySize;
        this.voltage = voltage;
        this.memoryConfiguration = memoryConfiguration;
        this.stock = stock;
        this.activeStock = activeStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public String getMemoryConfiguration() {
        return memoryConfiguration;
    }

    public void setMemoryConfiguration(String memoryConfiguration) {
        this.memoryConfiguration = memoryConfiguration;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getActiveStock() {
        return activeStock;
    }

    public void setActiveStock(Integer activeStock) {
        this.activeStock = activeStock;
    }
}
