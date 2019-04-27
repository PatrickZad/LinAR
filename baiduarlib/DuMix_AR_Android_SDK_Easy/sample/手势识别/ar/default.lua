app_controller = ae.ARApplicationController:shared_instance()
app_controller:require('./scripts/include.lua')
app = AR:create_application(AppType.ImageTrack, "bear")
app:load_scene_from_json("res/simple_scene.json","demo_scene")
scene = app:get_current_scene()

local gesture_1 = 0
local gesture_2 = 0
local gesture_3 = 0
local gesture_4 = 0

app.on_loading_finish = function()
	app.device:enable_front_camera()
	app.device:open_imu(1)
	-- app:play_bg_music("/res/media/bgm.mp3", -1, 0)

    gesture_2 = 1
    gesture_4 = 0

    scene.right_1:set_visible(true)
    scene.right_2:set_visible(true)

    scene.StartButton.on_click = function()
    	scene.ContentPlane:set_visible(false)
    	scene.StartButton:set_visible(false)
        PaddleGesture:send_control_msg(1)
        scene.gesture_2:set_visible(true)
        -- scene.gesture_4:set_visible(true)
    end
	
	scene.right_1.on_click = function()
		io.write("PaddleGesture:send_control_msg(1)")
        PaddleGesture:send_control_msg(1)
    end

    scene.right_2.on_click = function()
    	io.write("PaddleGesture:send_control_msg(0)")
        PaddleGesture:send_control_msg(0)
    end


 --    scene.left_2.on_click = function()
 --        app:visible_type(ViewVisibleType.HideTopButton)
 --    end
	
end

resultType1 = 0
resultType2 = 0
resultType3 = 0

function validateResult( resultType)
	resultType1 = resultType2
	resultType2 = resultType3
	resultType3 = resultType
	if(resultType1 == resultType2  and resultType2 == resultType3) then
		return resultType
	end
	io.write("on_gesture_detected: interrupte")
	return 0
end

index = 0

PaddleGesture.on_gesture_detected = function(mapData)

	local count = mapData['gesture_count']

	io.write("on_gesture_detected:"..count)

	resultMap = mapData['gesture_result1']

	result = resultMap['type']
  score = resultMap['score']

	x1 = resultMap['x1']
	y1 = resultMap['y1']
	x2 = resultMap['x2']
	y2 = resultMap['y2']
	index = index+1

	io.write("on_gesture_detected index:" .. index .. " type:"..result.." score:"..score.." x1:"..x1.." y1:"..y1.." x2:"..x2.." y2:"..y2)

	result = validateResult(result)

  if (score < 0.8) then
    return
  end 

    if (result == 2) then
		if (gesture_2 == 1) then
			gesture_2 = 0
			-- gesture_4 = 0
			io.write("on_gesture_detected: palm")
			scene.gesture_2:set_visible(false)
			scene.bear:set_visible(true)
			scene.bear:pod_anim():anim_repeat(true):start()
			scene.bear:play_audio("/res/media/sound.wav", 1, 0)
			PaddleGesture:send_control_msg(0)
		end
    end

    if (result == 4) then
		if (gesture_4 == 1) then
			-- gesture_2 = 0
			gesture_4 = 0
			io.write("on_gesture_detected: ok")
			scene.gesture_4:set_visible(false)
			scene.bear:set_visible(true)
			scene.bear:pod_anim():anim_repeat(true):start()
			scene.bear:play_audio("/res/media/sound.wav", 1, 0)
		end
    end

end


app.on_target_found = function()

end

app.on_target_lost = function()

end


scene.node1.on_click = function()

end

