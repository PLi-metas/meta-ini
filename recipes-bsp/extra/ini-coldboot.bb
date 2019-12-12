SUMMARY = "S3 cold boot"
MAINTAINER = "ini Team"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PV = "1.0"

SRC_URI = "file://coldboot file://coldboot.sh"

inherit update-rc.d
INITSCRIPT_NAME = "coldboot"
INITSCRIPT_PARAMS = "start 30 0 ."

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/init.d ${D}${bindir}
    install -m 0755 ${WORKDIR}/coldboot.sh ${D}${sysconfdir}/init.d/coldboot
    install -m 0755 ${WORKDIR}/coldboot ${D}${bindir}/coldboot
}

pkg_preinst_${PN}_prepend() {
#!/bin/sh
chmod -x $D${sysconfdir}/init.d/coldboot
}

pkg_postinst_${PN}_append() {
#!/bin/sh
chmod 755 $D${sysconfdir}/init.d/coldboot
}

pkg_prerm_${PN}() {
#!/bin/sh
exit 0
}