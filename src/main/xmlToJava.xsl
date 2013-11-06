<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
    <xsl:strip-space elements="*"/>

    <xsl:template name="annotation">
        <xsl:text>@</xsl:text><xsl:value-of select="name(.)"/><xsl:text>(</xsl:text>
        <xsl:for-each select="@*">
            <xsl:value-of select="name()"/><xsl:text>="</xsl:text><xsl:value-of select="."/><xsl:text>"</xsl:text>
            <xsl:if test="not(position() = last())"><xsl:text>, </xsl:text></xsl:if>
        </xsl:for-each>
        <xsl:if test="*">
            <xsl:text>{&#xa;</xsl:text>
            <xsl:for-each select="*">
                <xsl:text>    </xsl:text>
                <xsl:call-template name="annotation"/>
                <xsl:if test="not(position() = last())"><xsl:text>,&#xa;</xsl:text></xsl:if>
            </xsl:for-each>
            <xsl:text>&#xa;}</xsl:text>
        </xsl:if>
        <xsl:text>)</xsl:text>
    </xsl:template>

    <xsl:template match="/file">
        <xsl:text>package </xsl:text><xsl:value-of select="@package"/><xsl:text>;&#xa;</xsl:text>
        <xsl:text>&#xa;</xsl:text>

        <xsl:for-each select="import">
            <xsl:text>import </xsl:text><xsl:value-of select="@class"/><xsl:text>;&#xa;</xsl:text>
        </xsl:for-each>
        <xsl:text>&#xa;</xsl:text>

        <xsl:for-each select="class">
            <xsl:for-each select="annotations/*">
                <xsl:call-template name="annotation"/>
                <xsl:text>&#xa;</xsl:text>
            </xsl:for-each>

            <xsl:text>class </xsl:text><xsl:value-of select="@name"/>
            <xsl:if test="@extends">
                <xsl:text> extends </xsl:text><xsl:value-of select="@extends"/>
            </xsl:if>
            <xsl:text> {&#xa;</xsl:text>
            <xsl:for-each select="field">
                <xsl:for-each select="annotations/*">
                    <xsl:text>    </xsl:text>
                    <xsl:call-template name="annotation"/>
                    <xsl:text>&#xa;</xsl:text>
                </xsl:for-each>
                <xsl:text>    protected </xsl:text><xsl:value-of select="@type"/><xsl:text> </xsl:text><xsl:value-of select="@name"/><xsl:text>;&#xa;</xsl:text>
                <xsl:if test="not(position() = last())"><xsl:text>&#xa;</xsl:text></xsl:if>
            </xsl:for-each>
            <xsl:if test="@extends">
                <xsl:if test="constructor">
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:for-each select="constructor">
                        <xsl:text>    public </xsl:text>
                        <xsl:value-of select="../@name"/>
                        <xsl:text>(</xsl:text>
                        <xsl:for-each select="@*">
                            <xsl:value-of select="."/>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="name()"/>
                            <xsl:if test="not(position() = last())"><xsl:text>, </xsl:text></xsl:if>
                        </xsl:for-each>
                        <xsl:text>) {&#xa;        super(</xsl:text>
                        <xsl:for-each select="@*">
                            <xsl:value-of select="name()"/>
                            <xsl:if test="not(position() = last())"><xsl:text>, </xsl:text></xsl:if>
                        </xsl:for-each>
                        <xsl:text>);&#xa;    }&#xa;</xsl:text>
                    </xsl:for-each>
                </xsl:if>
            </xsl:if>
            <xsl:text>}&#xa;</xsl:text>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>