package org.zerock.api1014.product.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.api1014.category.domain.QCategory;
import org.zerock.api1014.category.domain.QCategoryProduct;
import org.zerock.api1014.product.domain.Product;
import org.zerock.api1014.product.domain.QAttachFile;
import org.zerock.api1014.product.domain.QProduct;
import org.zerock.api1014.product.domain.QReivew;

import java.util.List;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch{

    public ProductSearchImpl() {
        super(Product.class);
    }


    public Page<Product> list ( Pageable pageable) {
        log.info("--------------------------list----------------");

        QProduct product = QProduct.product;
        QReivew reivew = QReivew.reivew;

        QAttachFile attachFile = QAttachFile.attachFile;


        JPQLQuery<Product> query = from(product);
        query.leftJoin(reivew).on(reivew.product.eq(product));
        query.leftJoin(product.attachFiles, attachFile);

        query.where(attachFile.ord.eq(0));
        query.groupBy(product);

        //페이징처리
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<Tuple> tupleQuery =
                query.select(
                        product,
                        reivew.count(),
                        attachFile.fileName
                        );
        tupleQuery.fetch();



        return null;
    }

    @Override
    public Page<Product> listByCno(Long cno, Pageable pageable) {
        QProduct product = QProduct.product;
        QReivew reivew = QReivew.reivew;

        QAttachFile attachFile = QAttachFile.attachFile;
        QCategoryProduct categoryProduct = QCategoryProduct.categoryProduct;


        JPQLQuery<Product> query = from(product);
        query.leftJoin(reivew).on(reivew.product.eq(product));
        query.leftJoin(categoryProduct).on(categoryProduct.product.eq(product));
        query.leftJoin(product.attachFiles, attachFile);

        query.where(attachFile.ord.eq(0));
        query.where(categoryProduct.category.cno.eq(cno));
        query.groupBy(product);

        //페이징처리
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<Tuple> tupleQuery =
                query.select(
                        product,
                        reivew.count(),
                        attachFile.fileName
                );
        List<Tuple> tupleList = tupleQuery.fetch();

        tupleList.stream().map(tuple -> {
            log.info(tuple);

            return null;
        });

        return null;
    }
}
