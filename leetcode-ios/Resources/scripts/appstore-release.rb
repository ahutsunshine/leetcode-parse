require 'xcodeproj'
 
bundleId = 'com.liulishuo.sprout'
devTeam = '8PJZBHFZQF'

project_path = 'Source/Sprout.xcodeproj'
puts(project_path)
project = Xcodeproj::Project.open(project_path)
 
app  =  project.targets[0]
 
puts app.name
 
app.build_configurations.each do |config|
 config.build_settings['PRODUCT_BUNDLE_IDENTIFIER'] = bundleId
 config.build_settings['DEVELOPMENT_TEAM'] = devTeam
end
 
project::save()