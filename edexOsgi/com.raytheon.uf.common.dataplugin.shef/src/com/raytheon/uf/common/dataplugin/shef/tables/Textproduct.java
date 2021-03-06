/**
* This software was developed and / or modified by Raytheon Company,
* pursuant to Contract DG133W-05-CQ-1067 with the US Government.
* 
* U.S. EXPORT CONTROLLED TECHNICAL DATA
* This software product contains export-restricted data whose
* export/transfer/disclosure is restricted by U.S. law. Dissemination
* to non-U.S. persons whether in the United States or abroad requires
* an export license or other authorization.
* 
* Contractor Name:        Raytheon Company
* Contractor Address:     6825 Pine Street, Suite 340
*                         Mail Stop B8
*                         Omaha, NE 68106
*                         402.291.0100
* 
* See the AWIPS II Master Rights File ("Master Rights File.pdf") for
* further licensing information.
**/
package com.raytheon.uf.common.dataplugin.shef.tables;
// default package
// Generated Oct 17, 2008 2:22:17 PM by Hibernate Tools 3.2.2.GA

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Textproduct generated by hbm2java
 * 
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Oct 17, 2008                        Initial generation by hbm2java
 * Aug 19, 2011      10672     jkorman Move refactor to new project
 * Oct 07, 2013       2361     njensen Removed XML annotations
 * 
 * </pre>
 * 
 * @author jkorman
 * @version 1.1
 */
@Entity
@Table(name = "textproduct")
@com.raytheon.uf.common.serialization.annotations.DynamicSerialize
public class Textproduct extends com.raytheon.uf.common.dataplugin.persist.PersistableDataObject implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement
    private TextproductId id;

    @com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement
    private String prodtype;

    @com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement
    private Integer issnum;

    @com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement
    private String product;

    public Textproduct() {
    }

    public Textproduct(TextproductId id, String prodtype) {
        this.id = id;
        this.prodtype = prodtype;
    }

    public Textproduct(TextproductId id, String prodtype, Integer issnum,
            String product) {
        this.id = id;
        this.prodtype = prodtype;
        this.issnum = issnum;
        this.product = product;
    }

    @EmbeddedId
    @AttributeOverrides( {
            @AttributeOverride(name = "productId", column = @Column(name = "product_id", nullable = false, length = 10)),
            @AttributeOverride(name = "producttime", column = @Column(name = "producttime", nullable = false, length = 29)),
            @AttributeOverride(name = "postingtime", column = @Column(name = "postingtime", nullable = false, length = 29)) })
    public TextproductId getId() {
        return this.id;
    }

    public void setId(TextproductId id) {
        this.id = id;
    }

    @Column(name = "prodtype", nullable = false, length = 1)
    public String getProdtype() {
        return this.prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype;
    }

    @Column(name = "issnum")
    public Integer getIssnum() {
        return this.issnum;
    }

    public void setIssnum(Integer issnum) {
        this.issnum = issnum;
    }

    @Column(name = "product")
    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

}
