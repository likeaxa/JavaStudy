package design_pattern.decorator;

/**
 * 为 shape 添加发光功能
 */
public class ShapeLightingDecorator implements Shape {

    private Shape shape;

    public ShapeLightingDecorator(Shape shape) {
        this.shape = shape;
    }

    public void lighting(){
        System.out.println("lighting when draw");
    }

    @Override
    public void draw() {
        shape.draw();
        lighting();
    }
}
