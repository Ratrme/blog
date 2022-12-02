package com.lxy.utils;


import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

/**
 * @Author lxy
 * @Description markdown转换
 * @Date 2022/11/30 11:16
 */

public class MarkdownUtils {

    /**
     * markdown格式转换位html格式
     * @param markdown markdown格式
     * @return html格式
     */
    public static String markdownToHtml(String markdown){
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }


    /**
     * 增加扩展(标题锚点，表格生成)
     * markdown格式转换位html格式
     * @param markdown markdown格式
     * @return html格式
     */
    public static  String markdownToHtmlExtensions(String markdown) {
        //h标题生成id
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //转换table的HTML
        List<Extension> tableExtensions = Collections.singletonList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtensions)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(tableExtensions)
                .attributeProviderFactory(attributeProviderContext -> new CustomAttributeProvider())
                .build();
        return renderer.render(document);
    }

    /**
     * 处理标签的属性
     */
    private static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            //改变a标签的target属性为_blank
            if (node instanceof Link) {
                map.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                map.put("class", "ui celled table");
            }
        }
    }
}
