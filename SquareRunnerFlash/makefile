NAME=SRF
MAIN=Main

WIDTH=640
HEIGHT=480
FPS=30
COLOR=FFFFFF

RES_FILE=obj/$(NAME)Res.swf
LIB_FILE=obj/$(NAME)Lib.xml
SWF_FILE=bin/$(NAME).swf

CLASSPATH=-cp src
OUTPUT=-swf9 $(SWF_FILE)
SWF_HEADER=-swf-header $(WIDTH):$(HEIGHT):$(FPS):$(COLOR)
SWF_LIB=-swf-lib $(RES_FILE)
MAIN_CLASS=-main $(MAIN)

$(SWF_FILE):$(RES_FILE)
	haxe $(CLASSPATH) $(OUTPUT) $(SWF_HEADER) $(SWF_LIB) $(MAIN_CLASS)
$(RES_FILE):$(LIB_FILE)
	swfmill simple $(LIB_FILE) $(RES_FILE)
build:
	haxe $(CLASSPATH) $(OUTPUT) $(SWF_HEADER) $(SWF_LIB) $(MAIN_CLASS)
resources:
	swfmill simple $(LIB_FILE) $(RES_FILE)
run:
	flashplayer $(SWF_FILE)
runWin32:
	projector $(SWF_FILE)
tarbin:
	tar cfvz $(NAME)_bin.tar.gz bin
tarsrc:
	tar cfvz $(NAME)_src.tar.gz src
tarres:
	tar cfvz $(NAME)_res.tar.gz aud bac snd spr
tarall:
	tar cfvz $(NAME)_all.tar.gz ..
