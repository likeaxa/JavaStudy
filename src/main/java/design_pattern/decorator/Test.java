package design_pattern.decorator;

/**
 * 装饰器模式，为原有的类增强功能
 */
public class Test {
    public static void main(String[] args) {
        // 普通矩形
        Shape rectangle = new Rectangle();
        rectangle.draw();

        // 装饰过发光功能的矩形
        Shape lightingRectAngle = new ShapeLightingDecorator(rectangle);
        lightingRectAngle.draw();
    }
}
