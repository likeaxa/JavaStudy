package Spring.demo1.ioc;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname ClassPathXmlApplicationContext
 * @Date 2019/9/2 10:46
 * @Created by yaoxinjian
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private static final Map<String,Object> BEAN_MAP = new ConcurrentHashMap<>();

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String xmlPath) throws Exception {

        SAXBuilder sb = new SAXBuilder();
        // 构造文档对象

        // 同级目录下加载
//      Document doc = sb.build(this.getClass().getResourceAsStream(xmlPath));
        // 从classpath下加载 ， 这里是maven项目加载的是
        // resources下面的bean.xml

        Document doc = sb.build(this.getClass().getClassLoader().getResourceAsStream(xmlPath));

        // 获取根元素的Beans
        Element root = doc.getRootElement();
        // 获取子元素bean
        List<Element> list = root.getChildren("bean");

        // 遍历
        for (Element element : list) {
            // 获取id的值
            String id = element.getAttributeValue("id");
            // 获取class的值
            String className = element.getAttributeValue("class");
            // 利用反射得到一个真实对象
            Object obj = Class.forName(className).newInstance();
            // 存入map
            BEAN_MAP.put(id, obj);
            // 获取配置中的property
            List<Element> propertyList = element.getChildren("property");
            // 遍历
            for (Element propertyElement : propertyList) {
                String propertyName = propertyElement.getAttributeValue("name");
                String beanName = propertyElement.getAttributeValue("ref");
                Object refObject = BEAN_MAP.get(beanName);
                // 构造一个setter方法
                String mothodName = "set" + propertyName.substring(0, 1).toUpperCase()
                        + propertyName.substring(1);
                Method method = obj.getClass().getMethod(mothodName,
                        refObject.getClass().getInterfaces()[0]);
                method.invoke(obj, refObject);
            }
        }
    }
    @Override
    public Object getBean(String id) {
        return BEAN_MAP.get(id);
    }
}
